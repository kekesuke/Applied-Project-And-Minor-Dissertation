package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applied_project_and_minor_dissertation.android.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.CompletableTransformer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;

public class ChatActivity extends AppCompatActivity {
    // instance variables
    private static  final String TAG = "ChatActivity";
    private ChatAdapter adapter;
    private List<String> dataSet = new ArrayList<>();
    private StompClient stompClient;
    private Disposable pingDisposable;
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private RecyclerView recyclerView;
    private Gson gson = new GsonBuilder().create();

    private CompositeDisposable compositeDisposable;

    public static final String LOGIN = "login";
    public static final String PASSCODE = "passcode";


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // binding the view and its elements too corresponding UI elements
        setContentView(R.layout.activity_test_chat);
        recyclerView = findViewById(R.id.recycle_view);
        adapter = new ChatAdapter(dataSet);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        // connecting too the stomp endpoint configured in the microservice
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.1:8086/chat/websocket");

        resetSubscriptions();
    }

    public void connectStomp(View v){
        List<StompHeader> headers = new ArrayList<>();
        headers.add(new StompHeader(LOGIN, "guest"));
        headers.add(new StompHeader(PASSCODE, "guest"));

        // this periodically checks if the stomp server is still running
        stompClient.withClientHeartbeat(1000).withServerHeartbeat(1000);

        resetSubscriptions();

        // multiple threads with exectuors that perform checks if the stomp connection is opened
        // closed or error is detected
        Disposable dispLifeCycle = stompClient.lifecycle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lifecycleEvent -> {
                    switch (lifecycleEvent.getType()){
                        case OPENED:
                            toast("Stomp Connection open");
                            break;
                        case ERROR:
                            toast("Stomp Connection Error");
                            break;
                        case CLOSED:
                            toast("Stomp Connection is Closed");
                            break;
                        case FAILED_SERVER_HEARTBEAT:
                            break;


                    }
                });

        compositeDisposable.add(dispLifeCycle);

        // multiple threads with exectuors that receive a response from the server.
        Disposable dispTopic = stompClient.topic("/topic/chat")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topicMessage -> {
                    Log.d(TAG, "Received " + topicMessage.getPayload());
                    addItem(gson.fromJson(topicMessage.getPayload(), MessageModel.class));
                }, throwable -> {
                    Log.e(TAG, "Error on subscirbe topic", throwable);
                });

        compositeDisposable.add(dispTopic);
        stompClient.connect(headers);
    }

    // disconnects from stomp server
    public void disconnectStomp(View v){
        stompClient.disconnect();
    }

    // sends message to stomp endpoint
    public void sendEchoViaStomp(View v){
        compositeDisposable.add(stompClient.send("/topic/message","Message STOMP " + timeFormat.format(new Date()))
                .compose(applySchedulers())
                .subscribe(() -> {
                    Log.d(TAG, "Stomp message send succuesffuly");
                }, throwable -> {
                    Log.e(TAG,"Error send STOMP Message", throwable);
                    toast(throwable.getMessage());
        }));
    }

    // adds item too recycle view
    public void addItem(MessageModel message)
    {
        dataSet.add(message.getMessage() + " ---- " + timeFormat.format(new Date()));
    }

    // returns a  function object too be passed into sendEchoViaStomp which handles the different events
    // forfor
    public CompletableTransformer applySchedulers(){
        return upstream -> upstream
                .unsubscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    // Used to clear existing subscription too topics
    public void resetSubscriptions(){
       if(compositeDisposable != null){
           compositeDisposable.dispose();
       }

       compositeDisposable = new CompositeDisposable();
    }

    // closes stomp connection
    @Override
    public void onDestroy(){
        stompClient.disconnect();

        if(pingDisposable != null) pingDisposable.dispose();
        if(compositeDisposable != null) compositeDisposable.dispose();
        super.onDestroy();
    }


    // Method used too display text too screen
    public void toast(String text){
        Log.i(TAG, text);
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }




}

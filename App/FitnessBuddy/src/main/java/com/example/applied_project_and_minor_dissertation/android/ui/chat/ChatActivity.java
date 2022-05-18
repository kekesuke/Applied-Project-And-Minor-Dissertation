package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;
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
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;

public class ChatActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_test_chat);
        recyclerView = findViewById(R.id.recycle_view);
        adapter = new ChatAdapter(dataSet);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "s://192.168.1.1:8086/chat/websocket");

        resetSubscriptions();
    }

    public void connectStomp(View v){
        List<StompHeader> headers = new ArrayList<>();
        headers.add(new StompHeader(LOGIN, "guest"));
        headers.add(new StompHeader(PASSCODE, "guest"));

        stompClient.withClientHeartbeat(1000).withServerHeartbeat(1000);

        resetSubscriptions();

        Disposable dispLifeCycle = stompClient.lifecycle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lifecycleEvent -> {
                    switch (lifecycleEvent.getType()){
                        case OPENED:
                            // TODO: 18/05/2022
                            break;
                        case ERROR:
                            // TODO: 18/05/2022
                            break;
                        case CLOSED:
                            // TODO: 18/05/2022
                            break;
                        case FAILED_SERVER_HEARTBEAT:
                            break;


                    }
                });

        compositeDisposable.add(dispLifeCycle);

        // Recieve greetings
        Disposable dispTopic = stompClient.topic("/topic/greetings")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topicMeesage -> {
                    Log.d(TAG, "Received " + topicMeesage.getPayload());
                    addItem(gson.fromJson(topicMeesage.getPayload(), EchoModel.class));
                }, throwable -> {
                    Log.e(TAG, "Error on subscirbe topic", throwable);
                });
        compositeDisposable.add(dispTopic);
        stompClient.connect(headers);
    }

    public void disconnectStomp(View v){
        stompClient.disconnect();
    }

    public void sendEchoViaStomp(View v){
        compositeDisposable.add(stompClient.send("/topic/hello-msg-mapping","Echo STOMP " + timeFormat.format(new Date()))
                .compose(applySchedulers())
                .subscribe(() -> {
                    Log.d(TAG, "Stomp echo send succuesffuly");
                }, throwable -> {
                    Log.e(TAG,"Error send STOMP echo", throwable);
                    toast(throwable.getMessage());
        }));
    }

    public void sendEchoViaRest(View v){
        pingDisposable = ChatRestClient.getInstance().getRepo()
                .sendRestEch("Echo Rest " + timeFormat.format(new Date()))
                .compose(applySchedulers())
                .subscribe(() -> {
                    Log.d(TAG, "Rest echo send successfully");
                }, throwable -> {
                    Log.e(TAG, "Error send REST echo", throwable);
                    toast(throwable.getMessage());
                });
    }

    public void addItem(EchoModel echoModel)
    {
        dataSet.add(echoModel.getEcho() + " ---- " + timeFormat.format(new Date()));
    }

    public CompletableTransformer applySchedulers(){
        return upstream -> upstream
                .unsubscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void resetSubscriptions(){
       if(compositeDisposable != null){
           compositeDisposable.dispose();
       }

       compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy(){
        stompClient.disconnect();

        if(pingDisposable != null) pingDisposable.dispose();
        if(compositeDisposable != null) compositeDisposable.dispose();
        super.onDestroy();
    }

    public void toast(String text){
        Log.i(TAG, text);
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }




}

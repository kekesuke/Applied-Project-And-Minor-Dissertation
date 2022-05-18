package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatRestClient {

    public static final String URL = "http://192.168.1.1:8086/";

    private static ChatRestClient instace;
    private static final Object lock = new Object();

    public static ChatRestClient getInstance(){
        ChatRestClient instance = ChatRestClient.instace;
        if(instance == null)
        {
            synchronized (lock){
                instance = ChatRestClient.instace;
                if(instance == null){
                    ChatRestClient.instace = new ChatRestClient();
                }
            }
        }
        return  instance;
    }

    private final Repository repo;

    private ChatRestClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        repo = retrofit.create(Repository.class);
    }

    public Repository getRepo(){return  repo;}
}




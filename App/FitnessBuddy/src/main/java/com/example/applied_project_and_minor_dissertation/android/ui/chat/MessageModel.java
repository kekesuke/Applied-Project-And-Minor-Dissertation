package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import kotlinx.serialization.Serializable;

// this class is used too send/recieve messages from the server
@Serializable
public class MessageModel {
    private String message;

    public MessageModel(){

    }

    public  String getMessage(){ return message;}

    public void setMessage(String message){this.message = message;}
}

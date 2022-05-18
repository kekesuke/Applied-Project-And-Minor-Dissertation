package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import kotlinx.serialization.Serializable;

@Serializable
public class EchoModel {
    private String echo;

    public EchoModel(){

    }

    public  String getEcho(){ return echo;}

    public void setEcho(String echo){this.echo = echo;}
}

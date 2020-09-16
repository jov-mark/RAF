package com.example.raf.model;

import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Message {

    @Exclude
    private final static SimpleDateFormat format = new SimpleDateFormat("hh:mm a dd.MM.yyyy");

    private String text="";
    private String id="";
    private String senderId="";
    private String receiverId="";
    private String name;
    private String time;

    public Message(){}

    public Message(String messageText, String receiverId, String senderId){
        this.text = messageText;
        this.id = receiverId+":"+senderId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        Date currTime = Calendar.getInstance().getTime();
        time = format.format(currTime);
    }

    public String getSenderId() {
        return senderId;
    }

    public String getText() {
        return this.text;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public String toString(){
        return this.text+" "+id+" "+senderId+" "+receiverId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTime(){
        return time;
    }
}

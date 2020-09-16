package com.example.raf.model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {

    private List<Message> messages;
    private String id;

    public Conversation(){}

    public Conversation(String id){
        this.messages = new ArrayList<>();
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void addMessages(List<Message> messages){
        this.messages.clear();
        this.messages.addAll(messages);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

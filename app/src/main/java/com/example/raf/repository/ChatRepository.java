package com.example.raf.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.raf.livedata.ChatLiveData;
import com.example.raf.livedata.ConvoLiveData;
import com.example.raf.model.Conversation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatRepository {

    private DatabaseReference reference;
    private ChatLiveData chatLiveData;

    public ChatRepository(Application application){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("conversations");
        chatLiveData = new ChatLiveData();
    }


    public void updateConversation(String convoId,Conversation convo){
        reference.child(convoId).setValue(convo);
    }

    public void initConversation(String convoId){
        reference.child(convoId).setValue(new Conversation());
    }

    public LiveData<Conversation> getConversationLiveData(String convoId){
        return new ConvoLiveData(convoId);
    }

    public ChatLiveData getMessages(String convoId){
        return chatLiveData;
    }
}

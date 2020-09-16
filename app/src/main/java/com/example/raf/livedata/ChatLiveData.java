package com.example.raf.livedata;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.raf.model.Conversation;
import com.example.raf.model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatLiveData extends LiveData<List<Conversation>> {

    private DatabaseReference reference;
    private ValueEventListener eventListener;


    public ChatLiveData(){
        reference = FirebaseDatabase.getInstance().getReference().child("conversations");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Conversation> conversations = new ArrayList<>();

                if(dataSnapshot.getValue() == null){
                    return;
                }
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Conversation convo = new Conversation(ds.getKey());
                    List<Message> messageList = new ArrayList<>();
                    for (DataSnapshot dsChild : ds.getChildren()) {
                        Message mess = dsChild.getValue(Message.class);
                        messageList.add(mess);
                    }
                    convo.addMessages(messageList);
                    conversations.add(convo);
                }
                setValue(conversations);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
    }

    @Override
    protected void onActive() {
        super.onActive();
        reference.addValueEventListener(eventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        reference.removeEventListener(eventListener);
    }
}

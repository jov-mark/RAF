package com.example.raf.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.raf.model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WallLiveData extends LiveData<List<Message>> {

    private DatabaseReference reference;
    private ValueEventListener eventListener;

    public WallLiveData(){
        reference = FirebaseDatabase.getInstance().getReference().child("wall");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Message> posts = new ArrayList<>();

                if(dataSnapshot.getValue()==null)
                    return;

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Message post = ds.getValue(Message.class);
                    posts.add(post);
                }

                setValue(posts);
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

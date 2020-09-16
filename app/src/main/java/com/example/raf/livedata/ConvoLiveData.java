package com.example.raf.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.raf.model.Conversation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConvoLiveData extends LiveData<Conversation> {

    private DatabaseReference reference;
    private ValueEventListener valueEventListener;

    public ConvoLiveData(String convoId){
        reference = FirebaseDatabase.getInstance().getReference().child("conversations/"+convoId);
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null)
                    return;
                Conversation convo = dataSnapshot.getValue(Conversation.class);
                convo.setId(dataSnapshot.getKey());

                setValue(convo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

    }

    @Override
    protected void onActive() {
        super.onActive();
        reference.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        reference.removeEventListener(valueEventListener);
    }
}

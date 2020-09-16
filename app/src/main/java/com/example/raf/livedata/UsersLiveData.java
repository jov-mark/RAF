package com.example.raf.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.raf.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersLiveData extends LiveData<List<User>> {

    private DatabaseReference reference;
    private ValueEventListener eventListener;

    public UsersLiveData(){
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User> usersList = new ArrayList<>();

                if (dataSnapshot.getValue() == null) {
                    return;
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    user.setIndexId(ds.getKey());
                    usersList.add(user);
                }
                setValue(usersList);
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

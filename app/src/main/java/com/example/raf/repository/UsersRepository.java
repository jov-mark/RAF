package com.example.raf.repository;

import android.app.Application;

import com.example.raf.livedata.UsersLiveData;
import com.example.raf.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsersRepository {

    private DatabaseReference reference;

    private UsersLiveData usersLiveData;

    public UsersRepository(Application application){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("users");
        usersLiveData = new UsersLiveData();
    }

    public UsersLiveData getUsersList(){
        return usersLiveData;
    }

}

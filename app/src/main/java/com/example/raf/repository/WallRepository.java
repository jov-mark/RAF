package com.example.raf.repository;

import androidx.lifecycle.LiveData;

import com.example.raf.livedata.WallLiveData;
import com.example.raf.model.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WallRepository {

    private DatabaseReference reference;
    private WallLiveData wallLiveData;

    public WallRepository(){
        reference = FirebaseDatabase.getInstance().getReference().child("wall");
        wallLiveData = new WallLiveData();
    }

    public LiveData<List<Message>> getWallLiveData(){
        return wallLiveData;
    }

    public void addPost(Message post){
        reference.push().setValue(post);
    }
}

package com.example.raf.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.raf.model.Message;
import com.example.raf.repository.WallRepository;

import java.util.List;

public class WallViewModel extends AndroidViewModel {

    private WallRepository repository;

    public WallViewModel(@NonNull Application application) {
        super(application);

        repository = new WallRepository();
    }

    public LiveData<List<Message>> getWallLiveData(){
        return repository.getWallLiveData();
    }

    public void addPost(Message post){
        repository.addPost(post);
    }
}

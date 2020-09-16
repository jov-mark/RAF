package com.example.raf.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.raf.livedata.UsersLiveData;
import com.example.raf.repository.UsersRepository;

public class UsersViewModel extends AndroidViewModel {

    private UsersRepository repository;


    public UsersViewModel(@NonNull Application application) {
        super(application);

        repository = new UsersRepository(application);
    }

    public UsersLiveData getUsersList(){
        return repository.getUsersList();
    }

}

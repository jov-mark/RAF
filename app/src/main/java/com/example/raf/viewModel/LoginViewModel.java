package com.example.raf.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.raf.model.User;
import com.example.raf.model.UserResponse;
import com.example.raf.repository.AuthRepository;

public class LoginViewModel extends AndroidViewModel {

    private AuthRepository authRepository;

    public LoginViewModel(@NonNull Application application){
        super(application);

        authRepository = new AuthRepository(application);
    }

    public void loginUser(String indexId,String name){
        User user = new User(indexId,name);
        authRepository.storeUser(user);
    }

    public LiveData<UserResponse> getUserLiveData(){
        return authRepository.getUserLiveData();
    }
}

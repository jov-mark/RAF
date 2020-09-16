package com.example.raf.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.raf.model.UserResponse;
import com.example.raf.repository.AuthRepository;

public class SplashViewModel extends AndroidViewModel {

    private AuthRepository authRepository;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
    }

    public LiveData<UserResponse> getLoggedInUserLiveData(){
        return authRepository.getUser();
    }
}

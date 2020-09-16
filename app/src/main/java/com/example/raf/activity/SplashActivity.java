package com.example.raf.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.raf.R;
import com.example.raf.model.UserResponse;
import com.example.raf.viewModel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashViewModel viewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        viewModel.getLoggedInUserLiveData().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if(userResponse.isSuccessful()){
                    startNextActivity(MainActivity.class);
                } else{
                    startNextActivity(LoginActivity.class);
                }
            }
        });
    }

    private void startNextActivity(final Class activityClass){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, activityClass);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}

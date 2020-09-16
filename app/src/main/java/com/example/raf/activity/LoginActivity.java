package com.example.raf.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raf.R;
import com.example.raf.model.UserResponse;
import com.example.raf.viewModel.LoginViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText etxtUsername;
    private EditText etxtIndex;
    private Button btnLogin;

    private String indexPattern = "[Rr][MmNn]-[0-9][0-9]-[0-9][0-9]";

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        initViewModel();
    }

    private void initUI(){
        etxtUsername = findViewById(R.id.etxt_login_username);
        etxtIndex = findViewById(R.id.etxt_login_index);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etxtUsername.getText().toString();
                String index = etxtIndex.getText().toString();

                Pattern pattern = Pattern.compile(indexPattern);
                Matcher matcher = pattern.matcher(index);

                if(name.trim().isEmpty() || !matcher.matches()){
                    Toast.makeText(LoginActivity.this, "Invalid input.", Toast.LENGTH_SHORT).show();
                } else{
                    viewModel.loginUser(index,name);
                }

            }
        });
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.getUserLiveData().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if(userResponse.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }   else{
                    Toast.makeText(LoginActivity.this, "Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

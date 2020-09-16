package com.example.raf.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raf.model.User;
import com.example.raf.model.UserResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AuthRepository {

    private static final String USERNAME_KEY = "usernameKey";
    private static final String INDEX_ID_KEY = "indexIdKey";

    private MutableLiveData<UserResponse> userLiveData;
    private MutableLiveData<UserResponse> userStoreLiveData;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private User currentUser;

    private DatabaseReference usersDatabaseReference;

    public AuthRepository(Application application){
        userLiveData = new MutableLiveData<>();
        userStoreLiveData = new MutableLiveData<>();

        String packageName = application.getPackageName();
        sharedPreferences = application.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        usersDatabaseReference = firebaseDatabase.getReference().child("users");

    }

    public LiveData<UserResponse> getUser(){
        String username = sharedPreferences.getString(USERNAME_KEY,null);
        String indexId = sharedPreferences.getString(INDEX_ID_KEY,null);

        UserResponse userResponse;

        if(username == null || indexId == null){
            User user = new User("","");
            userResponse = new UserResponse(user,false);
        } else{
            User user = new User(indexId,username);
            currentUser = user;
            userResponse = new UserResponse(user,true);
        }
        userLiveData.setValue(userResponse);

        return userLiveData;
    }

    public void storeUser(User user){
        String username = user.getName();
        String indexId = user.getIndexId();

        usersDatabaseReference.child(indexId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        editor.putString(USERNAME_KEY, username);
                        editor.putString(INDEX_ID_KEY, indexId);
                        editor.commit();

                        UserResponse userResponse = new UserResponse(user, true);
                        userStoreLiveData.setValue(userResponse);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {;
                        UserResponse userResponse = new UserResponse(user,false);
                        userStoreLiveData.setValue(userResponse);
                    }
                });
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public LiveData<UserResponse> getUserLiveData(){
        return userStoreLiveData;
    }

    public void clearUser(){
        editor.clear();
        editor.commit();
    }
}

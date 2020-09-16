package com.example.raf.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.raf.model.Conversation;
import com.example.raf.repository.AuthRepository;
import com.example.raf.repository.ChatRepository;

public class ChatViewModel extends AndroidViewModel {

    private ChatRepository repository;
    private AuthRepository authRepository;

    public ChatViewModel(@NonNull Application application) {
        super(application);

        repository = new ChatRepository(application);
        authRepository = new AuthRepository(application);
    }

    public LiveData<Conversation> getConversationLiveData(String convoId){
        return repository.getConversationLiveData(convoId);
    }

    public void updateConversation(String convoId,Conversation convo){
        repository.updateConversation(convoId,convo);
    }

}

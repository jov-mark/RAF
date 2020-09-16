package com.example.raf.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raf.R;
import com.example.raf.adapter.MessageAdapter;
import com.example.raf.model.Conversation;
import com.example.raf.model.Message;
import com.example.raf.model.User;
import com.example.raf.model.UserResponse;
import com.example.raf.viewModel.ChatViewModel;
import com.example.raf.viewModel.MainViewModel;
import com.example.raf.viewModel.UsersViewModel;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    public static final String REC_USER_KEY = "recUserKey";
    public static final String REC_USER_NAME = "recUserName";

    private Conversation conversation;

    private RecyclerView recyclerView;
    private MessageAdapter adapter;

    private EditText etxtMessage;
    private ChatViewModel chatViewModel;
    private String convoId;
    private String recName;
    private String recUserId;
    private String sendUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initUsers();
        initViewModel();
        initView();
    }


    private void initViewModel(){

        conversation = new Conversation(convoId);

        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);

        chatViewModel.getConversationLiveData(convoId).observe(this, new Observer<Conversation>() {
            @Override
            public void onChanged(Conversation con) {
                if(con==null)
                    conversation = new Conversation(convoId);
                else
                    conversation = con;

                adapter.setData(conversation.getMessages());
            }
        });


    }


    private void initView(){
        TextView txtName = findViewById(R.id.txt_name);
        txtName.setText(recName);

        recyclerView = findViewById(R.id.recyclerView_chat_activity);
        adapter = new MessageAdapter(sendUserId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        etxtMessage = findViewById(R.id.etxt_message_chat);
        ImageButton btnSend = findViewById(R.id.btn_send_chat);
        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String messageText = etxtMessage.getText().toString();
                if(!messageText.equals("")) {
                    Message message = new Message(messageText, recUserId, sendUserId);
                    conversation.addMessage(message);
                    chatViewModel.updateConversation(convoId,conversation);
                }
            }
        });
    }

    private void initUsers(){
        recUserId = getIntent().getStringExtra(REC_USER_KEY);
        recName = getIntent().getStringExtra(REC_USER_NAME);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        sendUserId = sharedPreferences.getString("indexIdKey", null);



        convoId = sendUserId+":"+recUserId;
        String revConvoId = recUserId+":"+sendUserId;

        if(revConvoId.compareTo(convoId)<0){
            convoId = revConvoId;
        }
    }

}

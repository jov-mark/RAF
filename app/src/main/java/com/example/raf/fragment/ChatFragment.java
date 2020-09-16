package com.example.raf.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.raf.R;
import com.example.raf.activity.ChatActivity;
import com.example.raf.adapter.ChatAdapter;
import com.example.raf.model.User;
import com.example.raf.viewModel.UsersViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private UsersViewModel usersViewModel;
    private List<User> userList;

    private RecyclerView recyclerView;
    private ChatAdapter adapter;

    public static ChatFragment newInstance(){
        ChatFragment chatFragment = new ChatFragment();
        return chatFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_chat,container,false);
        initRecyclerView(fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userList = new ArrayList<>();

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        usersViewModel.getUsersList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList.clear();
                userList.addAll(users);
                adapter.setData(userList);
            }
        });
    }

    private void initRecyclerView(View fragmentView){
        recyclerView = fragmentView.findViewById(R.id.recyclerView_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChatAdapter();
        adapter.setOnItemClickedListener(new ChatAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(User user) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra(ChatActivity.REC_USER_KEY,user.getIndexId());
                intent.putExtra(ChatActivity.REC_USER_NAME,user.getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}

package com.example.raf.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.raf.R;
import com.example.raf.adapter.WallAdapter;
import com.example.raf.model.Message;
import com.example.raf.viewModel.WallViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WallFragment extends Fragment {

    private RecyclerView recyclerView;
    private WallAdapter adapter;
    private String userId;
    public String userName;
    private WallViewModel viewModel;
    private EditText etxt;

    public static WallFragment newInstance() {
        WallFragment wallFragment = new WallFragment();
        return wallFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_wall,container,false);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getActivity().getPackageName(), Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("indexIdKey", null);
        userName = sharedPreferences.getString("usernameKey",null);

        initRecycler(fragmentView);
        initView(fragmentView);

        return fragmentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(WallViewModel.class);
        viewModel.getWallLiveData().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter.setData(messages);
            }
        });
    }

    private void initRecycler(View fragmentView){
        recyclerView = fragmentView.findViewById(R.id.recyclerView_wall);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WallAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view){
        etxt = view.findViewById(R.id.etxt_message_wall);
        ImageButton btnSend= view.findViewById(R.id.btn_send_wall);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postText = etxt.getText().toString();
                if(!postText.equals("")) {
                    Message post = new Message(postText, "all", userId);
                    post.setName(userName);
                    viewModel.addPost(post);
                }
            }
        });

        ImageButton btnCamera = view.findViewById(R.id.btn_image);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

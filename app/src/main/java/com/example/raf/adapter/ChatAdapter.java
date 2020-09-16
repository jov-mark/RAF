package com.example.raf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raf.R;
import com.example.raf.model.User;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    private OnItemClickedListener onItemClickedListener;
    private List<User> userList;

    public ChatAdapter(){
        userList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitem_chat,parent,false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        User user = userList.get(position);
        holder.setUser(user);
        holder.txtUsername.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setData(List<User> users){
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    public class ChatHolder extends RecyclerView.ViewHolder{

        User user;
        TextView txtUsername;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);

            txtUsername = itemView.findViewById(R.id.txt_name_chat);
            txtUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickedListener != null) {
                        onItemClickedListener.onItemClicked(user);
                    }
                }
            });
        }

        public void setUser(User user){
            this.user = user;
        }
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener){
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener{
        void onItemClicked(User user);
    }
}

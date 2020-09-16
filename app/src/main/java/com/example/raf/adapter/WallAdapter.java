package com.example.raf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raf.R;
import com.example.raf.model.Message;

import java.util.ArrayList;
import java.util.List;

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.WallHolder> {

    private List<Message> data;

    public WallAdapter(){
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public WallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitem_wall,parent,false);
        return new WallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallHolder holder, int position) {
        Message post = data.get(position);
        holder.txtPost.setText(post.getText());
        holder.txtName.setText(post.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Message> posts){
        this.data.clear();
        this.data.addAll(posts);
        notifyDataSetChanged();
    }

    public class WallHolder extends RecyclerView.ViewHolder{

        TextView txtPost;
        TextView txtName;
        public WallHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name_wall);
            txtPost = itemView.findViewById(R.id.txt_message);
        }
    }
}

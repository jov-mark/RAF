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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private static final int RECEIVED_MESS = 0;
    private static final int SENT_MESS = 1;

    private List<Message> data;
    private String userId;

    public MessageAdapter(String userId){
        data = new ArrayList<>();
        this.userId = userId;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if(viewType == SENT_MESS){
             view = inflater.inflate(R.layout.listitem_message,parent,false);
        } else if (viewType == RECEIVED_MESS){
            view = inflater.inflate(R.layout.listitem_message_r,parent,false);
        }
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Message message = data.get(position);
        holder.txtMessage.setText(message.getText());
    }

    @Override
    public int getItemViewType(int position) {
        Message message = data.get(position);
        if(message.getSenderId()!=null && message.getSenderId().equals(userId)){
            return SENT_MESS;
        }
        return RECEIVED_MESS;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Message> messages){
        this.data.clear();
        this.data.addAll(messages);
        notifyDataSetChanged();
    }

    public class MessageHolder extends RecyclerView.ViewHolder{

        TextView txtMessage;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_message);
        }
    }
}

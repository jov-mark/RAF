package com.example.raf.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raf.model.Message;

public abstract class MessageHolder extends RecyclerView.ViewHolder {
    public MessageHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(Message message);
}

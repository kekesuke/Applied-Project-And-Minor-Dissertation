package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.applied_project_and_minor_dissertation.android.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.SimpleViewHolder> {

    private final List<String> dataSet;

    public ChatAdapter(List<String> data) {dataSet = data;}


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount(){return dataSet.size();}

    static class SimpleViewHolder extends RecyclerView.ViewHolder{
        final TextView textView;

        public SimpleViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

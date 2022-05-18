package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.applied_project_and_minor_dissertation.android.R;

import java.util.List;

// adapter used too display information in the recycle view for the chat activity.
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.SimpleViewHolder> {

    // instance variables
    private final List<String> dataSet;
    public ChatAdapter(List<String> data) {dataSet = data;}


    // Intilises the view holder.
    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false));
    }

    // for the text field in each item_layout set it equal too a corresponding piece of data
    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position));
    }

    // get the number of items
    @Override
    public int getItemCount(){return dataSet.size();}

    // this represents each item_layout in the recycle viewer.
    static class SimpleViewHolder extends RecyclerView.ViewHolder{
        final TextView textView;

        public SimpleViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

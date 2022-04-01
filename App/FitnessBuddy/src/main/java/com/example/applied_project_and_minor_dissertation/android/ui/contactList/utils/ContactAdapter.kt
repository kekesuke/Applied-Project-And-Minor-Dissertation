package com.example.applied_project_and_minor_dissertation.android.ui.contactList.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.String

class ContactAdapter(seed: Int): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.view.setText(String.valueOf(1))
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun getItemViewType(position: Int): Int {
        return android.R.layout.activity_list_item
    }
}


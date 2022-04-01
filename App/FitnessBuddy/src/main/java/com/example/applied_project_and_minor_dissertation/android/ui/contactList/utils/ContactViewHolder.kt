package com.example.applied_project_and_minor_dissertation.android.ui.contactList.utils

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val view: TextView = itemView.findViewById(android.R.id.text1)

    @JvmName("getView1")
    fun getView(): TextView?{
        return view
    }
}
package com.example.applied_project_and_minor_dissertation.android.ui.contactList.utils

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*
import java.lang.String

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val username1: TextView = itemView.contact_item_username

    fun bind(username: kotlin.String) {
        username1.setText(username)
    }
}
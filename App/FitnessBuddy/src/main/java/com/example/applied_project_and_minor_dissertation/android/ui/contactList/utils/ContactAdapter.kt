package com.example.applied_project_and_minor_dissertation.android.ui.contactList.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applied_project_and_minor_dissertation.android.R
import java.lang.String


class ContactAdapter(private var contacts: List<kotlin.String>, private val clickListener: (kotlin.String) -> Unit ): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        when(holder){
            is ContactViewHolder -> {
                holder.bind(contacts[position])

                holder.itemView.setOnClickListener{
                    clickListener(contacts[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun getItemViewType(position: Int): Int {
        return android.R.layout.activity_list_item
    }

    fun update(contactList: List<kotlin.String>){
        contacts = contactList
    }
}


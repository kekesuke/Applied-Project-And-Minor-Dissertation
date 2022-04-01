package com.example.applied_project_and_minor_dissertation.android.ui.`step-counter`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applied_project_and_minor_dissertation.android.R
import com.example.applied_project_and_minor_dissertation.android.ui.contactList.utils.ContactAdapter


class ContactListFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.activity_contacts, container, false)

        recyclerView = view.findViewById(R.id.contacts_recyclerview)
        if(recyclerView != null){
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.setLayoutManager(LinearLayoutManager(view.getContext()))
            recyclerView!!.setAdapter(ContactAdapter(1234))
        }

        return view

    }

}
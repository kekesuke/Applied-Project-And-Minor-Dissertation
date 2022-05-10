package com.example.applied_project_and_minor_dissertation.android.ui.contactList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applied_project_and_minor_dissertation.android.R
import com.example.applied_project_and_minor_dissertation.android.RetrofitHelper
import com.example.applied_project_and_minor_dissertation.android.SessionManager
import com.example.applied_project_and_minor_dissertation.android.ui.contactList.utils.Contact
import kotlinx.android.synthetic.main.activity_diet.*
import kotlinx.android.synthetic.main.activity_login.*
import misc.AuthApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ContactListFragment : Fragment() {
    private val contacts = ArrayList<String>()
    private var recyclerView: RecyclerView? = null
    private var accessToken: String? = null
    private var sessionManager: SessionManager? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.activity_contacts_list, container, false)

        recyclerView = view.findViewById(R.id.contacts_recyclerview)
        if(recyclerView != null){
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.setLayoutManager(LinearLayoutManager(view.getContext()))
           // recyclerView!!.setAdapter(ContactAdapter())
        }

       // findUsers(contacts)
        getToken()
        findUsers(contacts)
        return view

    }

    private fun getToken(): String? {
        val sessionManager: SessionManager? = getInstance()
        val user1 = sessionManager!!.getUserDetails()
        accessToken =  user1!![sessionManager!!.accessToken]
        return accessToken
    }

    fun getInstance(): SessionManager {
        return SessionManager(requireActivity())
    }

    private fun findUsers(onResult: ArrayList<String>) {
        Log.d("TestingToken", getToken().toString())
        val retrofit = RetrofitHelper.buildService(AuthApi::class.java)
            retrofit.findUsers(authHeader = "Bearer ${getToken()}").enqueue(
                object : Callback<List<String>> {
                    override fun onResponse(
                        call: Call<List<String>>,
                        response: Response<List<String>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Sucuesss", response.body().toString())
                            Log.d("d work", "d work")

                        } else {
                            Log.d("HTTP ERRORS", response.message().toString())
                            Log.d("HTTP ERRORS", response.code().toString())
                            Log.d("HTTP ERRORS", response.body().toString())
                            Log.d("HTTP ERRORS", response.errorBody().toString())
                            Log.d("HTTP ERRORS", response.raw().toString())
                            Log.d("HTTP ERRORS", "d work")
                        }

                    }

                    override fun onFailure(call: Call<List<String>>, t: Throwable) {
                        Log.d("Request Failed", "null")
                        Log.d("Request Failed", t.toString())
                        null

                    }

                }
            )



    }

}
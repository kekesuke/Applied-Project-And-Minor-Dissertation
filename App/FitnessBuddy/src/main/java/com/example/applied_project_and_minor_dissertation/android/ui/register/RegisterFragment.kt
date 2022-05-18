package com.example.applied_project_and_minor_dissertation.android.ui.register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.applied_project_and_minor_dissertation.android.*
import com.example.applied_project_and_minor_dissertation.android.Retrofit.RetrofitHelper
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityRegisterBinding
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.android.synthetic.main.activity_register.*
import com.example.applied_project_and_minor_dissertation.android.Retrofit.ServerEndPoints
import com.example.applied_project_and_minor_dissertation.android.responses.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.MyFrag
import com.example.applied_project_and_minor_dissertation.android.utils.SessionManager

val client = HttpClient(Android) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

class RegisterFragment() : Fragment(), MyFrag.MyFragInterace {
    private var _binding: ActivityRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // instance of sessionManager
        val session: SessionManager? = activity?.let { SessionManager(it.applicationContext) }

        // creating references to ui elemnets for activity
        val root = inflater.inflate(R.layout.activity_register, container, false)
        val buttonRegister: Button = root.findViewById(R.id.register_register_button)

        // upon clicking the register button pass in text from the fields into User object
        // that is then passed to the add user function
        buttonRegister.setOnClickListener {
            check()
            val username1 = register_editText_username.text.toString()
            val email1 = register_editText_email.text.toString()
            val password1 = register_editText_password.text.toString()
            val user = User(
                username = username1,
                email = email1,
                password = password1
            )
            addUser(user) {
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    ///////////////////////////////////////
    fun addUser(userData: User, onResult: (User?) -> Unit) {
        // configures retrofit too accept a list of predefined endpoints
        val retrofit = RetrofitHelper.buildService(ServerEndPoints::class.java)
        // sends the user data to a predefined path for registering in asynchronously
        retrofit.addUser(userData).enqueue(
           // server response
            object : Callback<User> {
                // if the request fails return nothing
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    //if the response is successful register the user
                    if (response.isSuccessful) {
                        val addedUser = response.body()
                        onResult(addedUser)
                    } else {
                        // if the response is unsuccessful display the error information for debugging
                        Log.d("Error", response.message().toString())
                        Log.d("Error", response.code().toString())
                        Log.d("Error", response.body().toString())
                        Log.d("Error", response.errorBody().toString())
                        Log.d("Error", response.raw().toString())
                    }


                }
            }
        )
    }

    override fun needsHide(id: Int) {
        val fragmentManager: FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        val myFrag: MyFrag = fragmentManager.findFragmentById(id) as MyFrag
        fragmentTransaction.hide(myFrag)
        fragmentTransaction.commit()
    }

    fun check() {
        val prefs: SharedPreferences? =
            context?.getSharedPreferences("userToken", Context.MODE_PRIVATE)
        val token = prefs?.getString("token", null)
        if (token != null) {
            Log.d("tokken", "this $token")
        }
    }
}
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
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityRegisterBinding
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.android.synthetic.main.activity_register.*
import misc.AuthApi
import misc.MyFrag
import misc.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val client = HttpClient(Android){
    install(JsonFeature){
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
        val session : SessionManager? = activity?.let { SessionManager(it.applicationContext) }

//        val galleryViewModel =
//            ViewModelProvider(this).get(DietViewModel::class.java)
//
//        _binding = ActivityRegisterBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.registerEditTextPassword
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
/*            if(prefs!!.getString("user_toke",null) == null){
                Log.d("token", "no token")
            }*/
            val root = inflater.inflate(R.layout.activity_register,container,false)
            val buttonRegister : Button = root.findViewById(R.id.register_register_button)
            buttonRegister.setOnClickListener{
                check()
                val username1 = register_editText_username.text.toString()
                val email1 = register_editText_email.text.toString()
                val password1 = register_editText_password.text.toString()
                val user = User(
                    username = username1,
                    email = email1,
                    password = password1
                )
                addUser(user){
                }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    ///////////////////////////////////////
    fun addUser(userData: User, onResult: (User?) -> Unit){
        val retrofit = RetrofitHelper.buildService(AuthApi::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful){
                        val addedUser = response.body()
                        onResult(addedUser)
                    } else {
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
        val fragmentManager : FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager!!.beginTransaction()
        val myFrag : MyFrag = fragmentManager.findFragmentById(id) as MyFrag
        fragmentTransaction.hide(myFrag)
        fragmentTransaction.commit()
    }

    fun check(){
        val prefs: SharedPreferences? = context?.getSharedPreferences("userToken", Context.MODE_PRIVATE)
        val token = prefs?.getString("token", null)
        if(token != null)
        {
            Log.d("tokken", "this $token")
        }
    }
}
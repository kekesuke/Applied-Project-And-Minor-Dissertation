package com.example.applied_project_and_minor_dissertation.android.ui.calories

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.*
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityLoginBinding
import com.example.applied_project_and_minor_dissertation.android.ui.UserRes
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: ActivityLoginBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.activity_login,container,false)
        val buttonLogin : Button = root.findViewById(R.id.login_button)
        buttonLogin.setOnClickListener{
            val username1 = login_editText_username.text.toString()
            val password1 = login_editText_password.text.toString()
            val user = UserLogin(
                username = username1,
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

    fun addUser(userData: UserLogin, onResult: (UserLogin?) -> Unit){
        val retrofit = RetrofitHelper.buildService(AuthApi::class.java)
        retrofit.loginUser(userData).enqueue(
            object : Callback<UserLogin> {
                override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                    if(response.isSuccessful){
                        val prefs: SharedPreferences? = context?.getSharedPreferences("userToken",MODE_PRIVATE)
                        val editor = prefs?.edit()
                        editor?.putString("token", response.body()?.accessToken)
                        val user = response.body()
                        onResult(user)
                        Log.d("Error", response.message().toString())
                        Log.d("Error", response.code().toString())
                        Log.d("Error", response.body().toString())
                        Log.d("Error", response.errorBody().toString())
                        Log.d("Error", response.raw().toString())
                        response.body()?.accessToken?.let { Log.d("Error", it) }

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
}
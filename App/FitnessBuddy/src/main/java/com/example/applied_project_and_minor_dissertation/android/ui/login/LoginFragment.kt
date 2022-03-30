package com.example.applied_project_and_minor_dissertation.android.ui.`step-counter`

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.*
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import misc.AuthApi
import misc.MyFrag
import misc.UserLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment(), MyFrag.MyFragInterace{

    private var _binding: ActivityLoginBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.activity_login,container,false)
        val buttonLogin : Button = root.findViewById(R.id.login_button)
        val buttonLogut : Button = root.findViewById(R.id.login_logout_button)
        buttonLogin.setOnClickListener{
            val username1 = login_editText_username.text.toString()
            val password1 = login_editText_password.text.toString()
            val user = UserLogin(
                username = username1,
                password = password1
            )
            addUser(user){}
        }
        buttonLogut.setOnClickListener{
            val session = getInstance()
            session.logout()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun getInstance(): SessionManager {
        return SessionManager(requireActivity())
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
                        if (editor != null) {
                            editor.apply()
                        }
                        val user = response.body()
                        onResult(user)
                        Log.d("Error", response.message().toString())
                        Log.d("Error", response.code().toString())
                        Log.d("Error", response.body().toString())
                        Log.d("Error", response.errorBody().toString())
                        Log.d("Error", response.raw().toString())
                        response.body()?.accessToken?.let { Log.d("Error", it) }
                        prefs!!.getString("token", null)?.let { Log.d("nock", it) }
                        response.body()?.username
                        response.body()?.password?.let { Log.d("testing", it) }
                        login_editText_username
                        val session = getInstance()
                        Toast.makeText(requireActivity().applicationContext, "User Login Status: " + session!!.isLoggedIn(), Toast.LENGTH_LONG).show();
                        session?.createLoginSession(response.body()!!.username, response.body()!!.email);
                        session?.let { Log.d("testing", it.EMAIL) }
                        session?.let { Log.d("testing", it.USER_NAME) }
                        Log.d("testing" , response.body()!!.username)
                        Log.d("testing" ,response.body()!!.email.toString()!!)


                        // get user data from session

                        // get user data from session
                        val user1 = session!!.getUserDetails()

                        // name

                        // name
                        val name = user1!![session!!.USER_NAME]

                        // email

                        // email
                        val email = user1!![session!!.EMAIL]

                        Log.d("please work", "$name $email")


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
        TODO("Not yet implemented")
    }


}
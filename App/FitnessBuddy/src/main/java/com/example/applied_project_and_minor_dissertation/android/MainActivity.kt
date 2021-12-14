package com.example.applied_project_and_minor_dissertation.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.Android

 val service = PostService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val username = register_editText_username.toString()
        val email = register_editText_email.toString()
        val password = register_editText_password.toString()

        val post : PostRequestSignUp = (username,email,password)



        register_register_button.setOnClickListener {
            Log.d("MainActivity", "username is: $username")
            Log.d("MainActivity", "Email is: $email")
            Log.d("MainActivity", "password is: $password")

           val posts = produceState<List<PostResponsetSignUp>>(
               intialValue = emptyList(),
               producer= {
                   value = service.create()
               }

           )



        }




    }

}

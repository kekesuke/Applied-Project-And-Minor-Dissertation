package com.example.applied_project_and_minor_dissertation.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.applied_project_and_minor_dissertation.android.Routes.HttpRoutes.BASE_URL
import com.example.applied_project_and_minor_dissertation.android.Routes.HttpRoutes.REGISTER
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.lifecycleScope
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.launch
import kotlin.math.log


val client = HttpClient(Android){
    install(JsonFeature){
        serializer = KotlinxSerializer()
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        register_register_button.setOnClickListener {
            val username = register_editText_username.text.toString()
            val email = register_editText_email.text.toString()
            val password = register_editText_password.text.toString()

            Log.d("MainActivity", "username is: $username")
            Log.d("MainActivity", "Email is: $email")
            Log.d("MainActivity", "password is: $password")


            //sendPost(username,email,password)
            lifecycleScope.launch {
                val result =  sendPost(username,email,password)

                onResult(result);


            }

    }
}
    suspend fun sendPost( username: String, email: String, password: String){
        val response: HttpResponse = client.post(REGISTER){
        contentType(ContentType.Application.Json)
        body = User(username,email,password)
        }

        val stringBody: String = response.receive();
        Log.d("MainActivity","HTTP response: $stringBody");

    }
    fun onResult(result: Unit){
        Log.d("MainActivity", "result:  $result.toString()")

    }
}




package com.example.applied_project_and_minor_dissertation.android.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.applied_project_and_minor_dissertation.android.R
import com.example.applied_project_and_minor_dissertation.android.Routes
import com.example.applied_project_and_minor_dissertation.android.User
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityRegisterBinding
import com.example.applied_project_and_minor_dissertation.android.ui.calories.DietViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

val client = HttpClient(Android){
    install(JsonFeature){
        serializer = KotlinxSerializer()
    }
}

class RegisterFragment() : Fragment() {
    private var _binding: ActivityRegisterBinding? = null

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
        val root = inflater.inflate(R.layout.activity_register,container,false)
        val buttonRegister : Button = root.findViewById(R.id.register_register_button)
        buttonRegister.setOnClickListener{
           Log.d("tag", "Called 1")
            scope.launch(Dispatchers.IO) {
                Register()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun Register()
    {
        Log.d("TAG", "TESTINNGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG")

            val username = register_editText_username.text.toString()
            val email = register_editText_email.text.toString()
            val password = register_editText_password.text.toString()

            Log.d("MainActivity", "username is: $username")
            Log.d("MainActivity", "Email is: $email")

            Log.d("MainActivity", "password is: $password")
            sendPost(username,email,password)
                lifecycleScope.launch {

                val result = sendPost(username, email, password)
                onResult(result);
            }

    }

    ///////////////////////////////////////
    suspend fun sendPost(username: String, email: String, password: String) {
        val response: HttpResponse = com.example.applied_project_and_minor_dissertation.android.client.post(
            Routes.HttpRoutes.REGISTER
        ) {
            contentType(ContentType.Application.Json)
            body = User(username, email, password)
        }

        val stringBody: String = response.receive();
        Log.d("MainActivity", "HTTP response: $stringBody");

    }

    fun onResult(result: Unit) {
        Log.d("MainActivity", "result:  $result.toString()")

    }
}
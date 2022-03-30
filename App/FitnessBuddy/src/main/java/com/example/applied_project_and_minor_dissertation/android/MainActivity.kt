package com.example.applied_project_and_minor_dissertation.android

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.Routes.HttpRoutes.REGISTER
import com.example.applied_project_and_minor_dissertation.android.ui.`step-counter`.StepsFragment
import com.example.applied_project_and_minor_dissertation.android.ui.`step-counter`.DietFragment
import com.example.applied_project_and_minor_dissertation.android.ui.`step-counter`.LoginFragment
import com.example.applied_project_and_minor_dissertation.android.ui.maps.MapFragment
import com.example.applied_project_and_minor_dissertation.android.ui.register.RegisterFragment
import com.google.android.material.navigation.NavigationView
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import misc.User


val client = HttpClient(Android) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ////////////////////////////////////////////////////////// edit

        val prefs: SharedPreferences = baseContext.getSharedPreferences("userToken", MODE_PRIVATE)
        val token = prefs.getString("user_toke", null)
        if(token == null)
        {
            Log.d("tokken", "this $token")
        }





        val session : SessionManager = SessionManager(applicationContext)
        Log.d("tokken", session.isLoggedIn().toString())


        val toolbar : Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //if(session.isLoggedIn()) {
            navView.setNavigationItemSelectedListener {
                it.isChecked = true
                when (it.itemId) {

                    R.id.nav_home -> replaceFragment(RegisterFragment(), it.title.toString())
                    R.id.nav_login -> replaceFragment(LoginFragment(), it.title.toString())
                    R.id.nav_maps -> replaceFragment(MapFragment(), it.title.toString())
                    R.id.nav_calories -> replaceFragment(StepsFragment(), it.title.toString())
                    R.id.nav_diet -> replaceFragment(DietFragment(), it.title.toString())
                }
                true
            }
       // }
        //else{
            /*navView.setNavigationItemSelectedListener {
                it.isChecked = true
                when (it.itemId) {

                    R.id.nav_home -> replaceFragment(RegisterFragment(), it.title.toString())
                    R.id.nav_login -> replaceFragment(LoginFragment(), "Login")
                    R.id.nav_maps -> replaceFragment(LoginFragment(), "Login")
                    R.id.nav_calories -> replaceFragment(LoginFragment(), "Login")
                    R.id.nav_diet -> replaceFragment(LoginFragment(), "Login")
                }
                true
            }*/
       // }

//////////////////////////////////////////////////////////edit

//sean code
//        register_register_button.setOnClickListener {
//            val username = register_editText_username.text.toString()
//            val email = register_editText_email.text.toString()
//            val password = register_editText_password.text.toString()
//
//            Log.d("MainActivity", "username is: $username")
//            Log.d("MainActivity", "Email is: $email")
//            Log.d("MainActivity", "password is: $password")
//
//
//            //sendPost(username,email,password)
//            lifecycleScope.launch {
//                val result = sendPost(username, email, password)
//
//                onResult(result);
//
//
//            }
//
//        }

//        override fun onBackPressed() {
//            if (DrawerLayout.(GravityCompat.START)) {
//                DrawerLayout.closeDrawer()
//            }
//            return super.onBackPressed()
//        }

    }

    public fun replace()
    {
        val intent = Intent(applicationContext, MapsActivity::class.java)
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment,title: String)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

            override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (toggle.onOptionsItemSelected(item)) {
                return true
            }

            return super.onOptionsItemSelected(item)
        }
    ///////////////////////////////////////
    suspend fun sendPost(username: String, email: String, password: String) {
        val response: HttpResponse = client.post(REGISTER) {
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




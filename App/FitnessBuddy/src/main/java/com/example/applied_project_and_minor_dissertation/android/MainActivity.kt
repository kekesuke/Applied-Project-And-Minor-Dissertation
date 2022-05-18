package com.example.applied_project_and_minor_dissertation.android


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.ui.chat.ChatFragment
import com.example.applied_project_and_minor_dissertation.android.ui.contactList.ContactListFragment
import com.example.applied_project_and_minor_dissertation.android.ui.diary.DiaryFragment
import com.example.applied_project_and_minor_dissertation.android.ui.stepCounter.StepsFragment
import com.example.applied_project_and_minor_dissertation.android.ui.diet.DietFragment
import com.example.applied_project_and_minor_dissertation.android.ui.login.LoginFragment
import com.example.applied_project_and_minor_dissertation.android.ui.maps.MapFragment
import com.example.applied_project_and_minor_dissertation.android.ui.register.RegisterFragment
import com.example.applied_project_and_minor_dissertation.android.ui.userProfile.UserProfileFragment
import com.google.android.material.navigation.NavigationView
import com.example.applied_project_and_minor_dissertation.android.utils.SessionManager

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    private lateinit var logout:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        if(session.isLoggedIn()) {
            navView.setNavigationItemSelectedListener {
                it.isChecked = true

                when (it.itemId) {

                    R.id.nav_home -> replaceFragment(RegisterFragment(), it.title.toString())
                    R.id.nav_login -> replaceFragment(LoginFragment(), it.title.toString())
                    R.id.nav_maps -> replaceFragment(MapFragment(), it.title.toString())
                    R.id.nav_calories -> replaceFragment(StepsFragment(), it.title.toString())
                    R.id.nav_diet -> replaceFragment(DietFragment(), it.title.toString())
                    R.id.nav_diary -> replaceFragment(DiaryFragment(), it.title.toString())
                    R.id.nav_userProfile -> replaceFragment(UserProfileFragment(), it.title.toString())
                    R.id.nav_chat -> replaceFragment(ChatFragment(), it.title.toString())
                    R.id.nav_contactList -> replaceFragment(ContactListFragment(), it.title.toString())
                }
                true
            }
        }
        else {
            navView.setNavigationItemSelectedListener {
                it.isChecked = true

                when (it.itemId) {

                    R.id.nav_home -> replaceFragment(RegisterFragment(), it.title.toString())
                    R.id.nav_login -> replaceFragment(LoginFragment(), it.title.toString())
                    R.id.nav_maps -> replaceFragment(LoginFragment(), it.title.toString())
                    R.id.nav_calories -> replaceFragment(LoginFragment(), it.title.toString())
                    R.id.nav_diet -> replaceFragment(LoginFragment(), it.title.toString())
                    R.id.nav_diary -> replaceFragment(LoginFragment(), it.title.toString())
                    R.id.nav_userProfile -> replaceFragment(
                        LoginFragment(),
                        it.title.toString()
                    )
                    R.id.nav_chat -> replaceFragment(LoginFragment(), it.title.toString())
 /*                   R.id.nav_contactList -> replaceFragment(
                        LoginFragment(),
                        it.title.toString()
                    )*/
                }
                true
            }

        }

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


}




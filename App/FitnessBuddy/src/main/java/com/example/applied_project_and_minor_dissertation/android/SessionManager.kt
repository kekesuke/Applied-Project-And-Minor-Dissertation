package com.example.applied_project_and_minor_dissertation.android

import android.content.Context
import android.content.SharedPreferences

class SessionManager(val context: Context) {

    private val PREF_NAME = "PrefFile"
    private val PRIVATE_MODE = 0
    private val LOGIN_TRUE = "IsLoggedIn"
    val USER_NAME = "username"
    val EMAIL = "email"

    val pref : SharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    val editor : SharedPreferences.Editor = pref.edit()

    fun createLoginSession(name:String, email: String?){
        editor.putBoolean(LOGIN_TRUE, true)
        editor.putString(USER_NAME, name)
        editor.putString(EMAIL, email)
        editor.commit()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(LOGIN_TRUE, false)
    }

    fun checkLogin(): Boolean {
        return isLoggedIn()
    }

    fun getUserDetails(): HashMap<String, String?>? {
        val user1 = HashMap<String, String?>()
        // user name
        user1[USER_NAME] = pref.getString(USER_NAME, null)

        // user email id
        user1[EMAIL] = pref.getString(EMAIL, null)

        // return user
        return user1
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }

}
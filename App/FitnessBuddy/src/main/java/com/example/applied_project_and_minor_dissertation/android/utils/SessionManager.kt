package com.example.applied_project_and_minor_dissertation.android.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(val context: Context) {

    // instance variables
    private val PREF_NAME = "PrefFile"
    private val PRIVATE_MODE = 0
    private val LOGIN_TRUE = "IsLoggedIn"
    val USER_NAME = "username"
    val EMAIL = "email"
    val accessToken = "accessToken";

    private var RECEIPIENT = "recipient"

    // access for internal storage as well as editing
    val pref : SharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    val editor : SharedPreferences.Editor = pref.edit()

    // creates a login session
    fun createLoginSession(name:String, email: String?, token: String?){
        editor.putBoolean(LOGIN_TRUE, true)
        editor.putString(USER_NAME, name)
        editor.putString(EMAIL, email)
        editor.putString(accessToken, token)
        editor.commit()
    }

    // checks if user is logged in
    fun isLoggedIn(): Boolean {
        return pref.getBoolean(LOGIN_TRUE, false)
    }


    // returns details of loggin session
    fun getUserDetails(): HashMap<String, String?>? {
        val user1 = HashMap<String, String?>()
        // user name
        user1[USER_NAME] = pref.getString(USER_NAME, null)

        // user email id
        user1[EMAIL] = pref.getString(EMAIL, null)

        //user token
        user1[accessToken] = pref.getString(accessToken, null)

        // return user
        return user1
    }

    fun getRecipient (): String? {
        return  pref.getString(RECEIPIENT,null)
    }

    fun setRecipient (Rece: kotlin.String){
        editor.putString(RECEIPIENT, Rece)
        editor.commit()
    }

    // logs user out of session
    fun logout(){
        editor.clear()
        editor.commit()
    }

}
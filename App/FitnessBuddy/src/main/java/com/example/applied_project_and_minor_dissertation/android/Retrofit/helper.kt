package com.example.applied_project_and_minor_dissertation.android.Retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    // client for sending HTTP requests
    private val client = OkHttpClient.Builder().build()
    val baseUrl = "http://34.244.215.223:8084"//inset ip here with regarding to the "http://" format folling your own ip or if the aws service is up 24/7 doesnt require change

    private val retrofit = Retrofit.Builder()
        // base url for requests
        .baseUrl(baseUrl) // change this IP for testing by your actual machine IP
        // allows the handling of complex data types
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        // builds the retrofit object
        .build()

    // creates the retrofit service
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}

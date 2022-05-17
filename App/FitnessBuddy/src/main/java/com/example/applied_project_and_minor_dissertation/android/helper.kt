package com.example.applied_project_and_minor_dissertation.android

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val client = OkHttpClient.Builder().build()
    val baseUrl = "http://34.244.215.223:8084/"//inset ip here with regarding to the "http://" format folling your own ip or if the aws service is up 24/7 doesnt require change

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl) // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}

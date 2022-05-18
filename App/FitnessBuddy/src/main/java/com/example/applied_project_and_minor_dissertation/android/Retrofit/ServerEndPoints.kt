package com.example.applied_project_and_minor_dissertation.android.Retrofit

import com.example.applied_project_and_minor_dissertation.android.responses.FooDResponse
import com.example.applied_project_and_minor_dissertation.android.responses.FoodDTO
import com.example.applied_project_and_minor_dissertation.android.responses.User
import com.example.applied_project_and_minor_dissertation.android.responses.UserLogin
import retrofit2.Call
import retrofit2.http.*

interface ServerEndPoints {


    @Headers("Content-Type: application/json")
    @POST("api/auth/signup/")
    fun addUser(@Body userData: User): Call<User>

    @Headers("Content-Type: application/json")
    @POST("api/auth/signin/")
    fun loginUser(@Body userLogin: UserLogin): Call<UserLogin>

    @Headers("Content-Type: application/json")
    @POST("api/diet/foodintake")
    fun sendFood(@Body food: FoodDTO): Call<List<FooDResponse>>

    @Headers("Content-Type: application/json")
    @GET("api/auth/fetchbymatch")
    fun findUsers(@Header("AUTHORIZATION") authHeader: String): Call<List<String>>



}
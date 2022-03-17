package misc

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
interface AuthApi {


    @Headers("Content-Type: application/json")
    @POST("api/auth/signup/")
    fun addUser(@Body userData: User): Call<User>

    @Headers("Content-Type: application/json")
    @POST("api/auth/signin/")
    fun loginUser(@Body userLogin: UserLogin): Call<UserLogin>

}
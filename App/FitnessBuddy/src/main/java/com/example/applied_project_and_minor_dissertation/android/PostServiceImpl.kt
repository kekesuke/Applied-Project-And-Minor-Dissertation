package com.example.applied_project_and_minor_dissertation.android

import com.example.applied_project_and_minor_dissertation.android.Routes.HttpRoutes.REGISTER
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl (
    private val client: HttpClient
): PostService {
    override suspend fun createPost(postRequestSignUp: PostRequestSignUp): PostResponsetSignUp? {
        return try{
            client.post<PostResponsetSignUp> {
                url(REGISTER)
                contentType(ContentType.Application.Json)
                body = postRequestSignUp
            }
        }catch(e: RedirectResponseException){
            println(e.response.status.description)
            null
        }catch(e: ClientRequestException){
            println(e.response.status.description)
            null
        }catch(e: ServerResponseException){
            println(e.response.status.description)
            null
        }catch(e: Exception){
            println(e.message)
            null
        }


    }

}
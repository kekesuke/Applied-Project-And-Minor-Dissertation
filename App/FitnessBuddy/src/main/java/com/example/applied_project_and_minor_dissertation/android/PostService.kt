package com.example.applied_project_and_minor_dissertation.android


import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.serializer

interface PostService {

    suspend fun createPost(postRequestSignUp: PostRequestSignUp): PostResponsetSignUp?

    companion object{
        fun create(): PostService{
            return PostServiceImpl(
                client =  HttpClient(Android){
                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(JsonFeature){
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}
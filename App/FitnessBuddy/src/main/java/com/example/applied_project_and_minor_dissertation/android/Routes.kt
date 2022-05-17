package com.example.applied_project_and_minor_dissertation.android

class Routes {
    object HttpRoutes{
        const val BASE_URL = "http://34.244.215.223:8084/"//main entry point of the application for retrofit
        const val REGISTER = "$BASE_URL" + "api/auth/signup"
    }
}
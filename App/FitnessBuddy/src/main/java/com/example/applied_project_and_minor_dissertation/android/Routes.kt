package com.example.applied_project_and_minor_dissertation.android

class Routes {
    object HttpRoutes{
        const val BASE_URL = "http://192.168.1.1:8080/"//main entry point of the application for retrofit
        const val REGISTER = "$BASE_URL" + "api/auth/signup"
    }
}
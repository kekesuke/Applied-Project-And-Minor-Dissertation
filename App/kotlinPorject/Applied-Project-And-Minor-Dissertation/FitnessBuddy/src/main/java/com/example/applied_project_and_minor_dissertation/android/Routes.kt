package com.example.applied_project_and_minor_dissertation.android

class Routes {
    object HttpRoutes{
        private const val BASE_URL = "http://localhost:8080/"
        const val REGISTER = "$BASE_URL" + "auth/signup"
    }
}
package com.example.applied_project_and_minor_dissertation.android

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin( val username: String,
                 val password: String,
                 val accessToken: String? = null)
package com.example.applied_project_and_minor_dissertation.android.responses

import kotlinx.serialization.Serializable

// used too send login requests too server
@Serializable
data class UserLogin( val username: String,
                 val password: String,
                 val accessToken: String? = null,
                 val email: String? = null)
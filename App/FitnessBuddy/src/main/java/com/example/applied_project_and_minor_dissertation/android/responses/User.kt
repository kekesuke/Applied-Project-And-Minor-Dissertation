package com.example.applied_project_and_minor_dissertation.android.responses

import kotlinx.serialization.Serializable

// used too send register requests too server
@Serializable
data class User( val username: String,
                 val email: String,
                 val password: String,
)
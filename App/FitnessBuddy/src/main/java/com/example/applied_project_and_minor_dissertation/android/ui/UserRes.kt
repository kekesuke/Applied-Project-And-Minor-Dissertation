package com.example.applied_project_and_minor_dissertation.android.ui

import kotlinx.serialization.Serializable
//model for request
@Serializable
data class UserRes( val id: Int,
                      val username: String,
                      val email: String,
                      val roles: Array<String>,
                      val accessToken: String,
                      val tokenType: String)
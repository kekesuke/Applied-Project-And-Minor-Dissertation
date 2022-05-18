package com.example.applied_project_and_minor_dissertation.android.responses

import kotlinx.serialization.Serializable

@Serializable
data class food(val foodName: String?,
                val foodWeight: Float?,
)
//structure of response request
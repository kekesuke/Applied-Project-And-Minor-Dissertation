package com.example.applied_project_and_minor_dissertation.android.responses

import kotlinx.serialization.Serializable

@Serializable
data class FooDResponse(
    val foodName: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val foodWeight: Float
)
//everything included in the food object recieved from the database
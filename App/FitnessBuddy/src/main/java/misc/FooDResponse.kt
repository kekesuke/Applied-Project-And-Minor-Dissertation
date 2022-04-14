package misc

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
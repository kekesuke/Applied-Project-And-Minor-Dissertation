package misc

import kotlinx.serialization.Serializable

@Serializable
data class food(val foodName: String?,
                val foodWeight: Float?,
)
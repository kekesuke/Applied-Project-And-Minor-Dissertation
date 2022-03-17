package misc

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin( val username: String,
                 val password: String,
                 val accessToken: String? = null,
                 val email: String? = null)
package fingerfire.com.extractbank.model

import java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    val account: String,
    val agency: String,
    val amount: Double
) : Serializable

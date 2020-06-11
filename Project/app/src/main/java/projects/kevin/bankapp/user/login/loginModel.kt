package projects.kevin.bankapp.user.login

import java.math.BigDecimal

data class UserAccount(
    val userId: Long,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: BigDecimal

)

data class LoginApiResponse(
    val userAccount: UserAccount,
    val error: Error? = null
)

data class LoginApiRequest(
    val user: String,
    val password: String
)

data class Error(
    val code: String,
    val message: String
)
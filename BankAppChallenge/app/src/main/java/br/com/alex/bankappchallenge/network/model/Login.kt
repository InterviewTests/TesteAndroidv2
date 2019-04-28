package br.com.alex.bankappchallenge.network.model

data class LoginResponse(
    val userAccount: UserAccount?,
    val error: Error?
)

data class LoginRequest(
    val user: String,
    val password: String
)

data class UserAccount(
    val userId: Long,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
)
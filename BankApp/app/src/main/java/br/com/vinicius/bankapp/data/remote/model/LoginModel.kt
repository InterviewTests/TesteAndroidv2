package br.com.vinicius.bankapp.data.remote.model

data class LoginModel(
    val agency: String,
    val balance: Double,
    val bankAccount: String,
    val name: String,
    val userId: Int
)
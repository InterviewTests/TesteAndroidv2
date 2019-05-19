package br.com.teste.santander.login.repository.model

data class UserAccount(
    val agency: String?,
    val balance: Double?,
    val bankAccount: String?,
    val name: String?,
    val userId: Int?
)
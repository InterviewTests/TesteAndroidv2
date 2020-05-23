package br.com.bankapp.domain.models

data class UserAccount(
    val userId: Int,
    val name: String?,
    val bankAccount: String?,
    val agency: String?,
    val balance: Double?
)
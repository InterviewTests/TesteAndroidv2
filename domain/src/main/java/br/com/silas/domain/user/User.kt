package br.com.silas.domain.user

open class User(
    val id: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
)
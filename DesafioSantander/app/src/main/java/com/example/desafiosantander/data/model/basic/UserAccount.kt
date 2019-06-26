package com.example.desafiosantander.data.model.basic

data class UserAccount(
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
)
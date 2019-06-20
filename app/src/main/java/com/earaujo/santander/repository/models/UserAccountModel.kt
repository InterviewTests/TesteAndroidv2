package com.earaujo.santander.repository.models

data class UserAccountModel(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
)
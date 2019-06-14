package com.accenture.santander.entity

data class UserAccount(
    var userId: Int = 1,
    var name: String = "Jose da Silva Teste",
    var bankAccount: String = "2050",
    var agency: String = "012314564",
    var balance: Double = 3.3445
)
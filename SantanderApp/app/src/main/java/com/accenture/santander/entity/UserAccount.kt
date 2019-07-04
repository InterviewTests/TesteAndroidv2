package com.accenture.santander.entity

import com.accenture.santander.interector.dataManager.entity.UserEntity

data class UserAccount(
    var userId: Int = 0,
    var name: String = "",
    var bankAccount: String = "",
    var agency: String = "",
    var balance: Double = 0.0
)
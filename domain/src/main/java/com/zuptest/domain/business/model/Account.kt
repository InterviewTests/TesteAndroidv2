package com.zuptest.domain.business.model

data class Account(
    val id: Int,
    val holder: String,
    val balance: Money,
    val bankInfo: BankInfo
)
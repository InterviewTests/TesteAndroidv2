package com.zuptest.santander.domain.business.model

import java.io.Serializable

data class Account(
    val id: Int,
    val holder: String,
    val balance: Money,
    val bankInfo: BankInfo
) : Serializable
package com.zuptest.santander.domain.business.model

import java.io.Serializable

data class BankInfo(
    val account: String,
    val agency: String) : Serializable
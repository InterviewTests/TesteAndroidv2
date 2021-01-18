package com.jeanjnap.domain.entity

import java.io.Serializable
import java.math.BigDecimal

data class UserAccount(
    val userId: Long?,
    val name: String?,
    val bankAccount: String?,
    val agency: String?,
    val balance: BigDecimal?
) : Serializable

package com.jeanjnap.domain.entity

import java.math.BigDecimal
import java.util.Date

data class Statement(
    val title: String?,
    val desc: String?,
    val date: Date?,
    val value: BigDecimal?
)

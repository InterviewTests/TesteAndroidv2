package com.santander.domain.entity.business

import java.text.NumberFormat
import java.util.*

data class Money(
    val value: Double
) {
    fun formatted(locale: Locale = Locale("pt", "BR")): String {
        return NumberFormat.getCurrencyInstance(locale).format(value).replace(" ", "")
    }
}
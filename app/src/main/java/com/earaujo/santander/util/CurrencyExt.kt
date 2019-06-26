package com.earaujo.santander.util

import java.text.NumberFormat
import java.util.*

fun Double.toBrl(): String {
    NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        currency = Currency.getInstance("BRL")
        return format(this@toBrl)
    }
}
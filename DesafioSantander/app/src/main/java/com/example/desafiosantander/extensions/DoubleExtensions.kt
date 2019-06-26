package com.example.desafiosantander.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.formatDoubleToMoney(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this)
}
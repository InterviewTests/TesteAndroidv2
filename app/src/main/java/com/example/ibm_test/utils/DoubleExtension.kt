package com.example.ibm_test.utils

import java.text.NumberFormat
import java.util.*

fun Double.toMoney(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR")) // Default local currency
    return numberFormat.format(this)
}

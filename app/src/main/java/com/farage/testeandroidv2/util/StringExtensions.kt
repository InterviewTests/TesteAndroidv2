package com.farage.testeandroidv2.util

import android.os.Build
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toMoney(): String {
    val num = this.replace(".", "")
    val numFormat = NumberFormat.getCurrencyInstance().format(num.toDouble())
    return numFormat
}

fun String.dateFormat(): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var date = LocalDate.parse(this)
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return date.format(dateFormat)
    }
    return this
}
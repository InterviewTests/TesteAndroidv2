package com.gustavo.bankandroid.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDisplayDateFormat(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val inputDateStr = "2013-06-24"
        val date = inputFormat.parse(inputDateStr)
        date?.let {
            return outputFormat.format(it)
        } ?: kotlin.run {
            return this
        }
    } catch (e: ParseException) {
        this
    }
}

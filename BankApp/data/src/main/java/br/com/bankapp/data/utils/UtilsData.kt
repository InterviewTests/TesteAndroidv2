package br.com.bankapp.data.utils

import java.text.SimpleDateFormat
import java.util.*


fun convertStringToDate(date: String): Date {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date) ?: Date()
}

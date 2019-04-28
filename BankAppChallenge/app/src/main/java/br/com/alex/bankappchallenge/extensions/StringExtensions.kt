package br.com.alex.bankappchallenge.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_DATE = "dd/MM/yyyy"
const val FORMAT_SERVER_DATE = "yyyy-MM-dd"

fun String.formatDate(): String {
    val parse = SimpleDateFormat(FORMAT_SERVER_DATE, Locale.getDefault()).parse(this)
    return SimpleDateFormat(FORMAT_DATE, Locale.getDefault()).format(parse)
}
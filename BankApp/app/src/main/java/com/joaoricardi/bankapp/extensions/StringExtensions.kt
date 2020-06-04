package com.joaoricardi.bankapp.extensions

import java.text.SimpleDateFormat

fun String.toBrDate(): String{
    val oldFormat = SimpleDateFormat("yyyy-MM-dd")
    val newFormat = SimpleDateFormat("dd/MM/yyyy")

    return newFormat.format(oldFormat.parse(this))
}
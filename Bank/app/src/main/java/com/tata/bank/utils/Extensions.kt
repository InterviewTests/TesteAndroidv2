package com.tata.bank.utils

import java.text.SimpleDateFormat

fun Double?.toReais() = "R$ %.2f".format(this ?: 0.0F).replace(".", ",")

fun String.toAgencyFormat(): String {
    val first = this.substring(0, 2)
    val middle = this.substring(2, this.length-1)
    val last = this.substring(this.length-1)

    return "$first.$middle-$last"
}

fun String.dateFormat(): String {
    return try {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val outFormat = SimpleDateFormat("dd/MM/yyyy")
        val result = inFormat.parse(this)

        outFormat.format(result)
    } catch (e: Exception) {
        this
    }
}
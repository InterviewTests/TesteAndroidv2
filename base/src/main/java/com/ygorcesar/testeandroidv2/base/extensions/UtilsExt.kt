package com.ygorcesar.testeandroidv2.base.extensions

import com.squareup.moshi.Moshi
import java.text.SimpleDateFormat
import java.util.*

fun String.Companion.empty() = ""

fun localeBrazil() = Locale("pt", "BR")

fun String.dateToFormatBr(): String {
    val date = SimpleDateFormat("yyyy-MM-dd", localeBrazil()).parse(this)
    return SimpleDateFormat("dd/MM/yyyy", localeBrazil()).format(date)
}


inline fun <reified T> Moshi.fromJson(json: String): T? =
    if (json.isNotBlank()) this.adapter(T::class.java).fromJson(json) else null

inline fun <reified T> Moshi.toJson(t: T): String = this.adapter(T::class.java).toJson(t) ?: ""
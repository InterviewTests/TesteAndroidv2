package dev.ornelas.bankapp.commons

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


private val simpleDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd") }

fun Date.formatDate(): String = simpleDateFormat.format(this)

fun String.parserDate(): Date? = simpleDateFormat.parse(this)
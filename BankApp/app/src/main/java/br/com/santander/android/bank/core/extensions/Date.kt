package br.com.santander.android.bank.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "dd/MM/yyyy"): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(this)
}
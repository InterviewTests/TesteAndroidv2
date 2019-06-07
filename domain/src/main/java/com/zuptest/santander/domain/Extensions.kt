package com.zuptest.santander.domain

import java.text.SimpleDateFormat
import java.util.*

fun Date.formattedPrint(): String {
    val fullDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
    return fullDateFormat.format(this)
}

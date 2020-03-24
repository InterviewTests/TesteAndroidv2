package com.example.ibm_test.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toShortDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(this)
}
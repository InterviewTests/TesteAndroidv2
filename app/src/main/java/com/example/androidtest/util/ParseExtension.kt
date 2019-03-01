package com.example.androidtest.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Date.toSimpleString(): String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)

fun Double.toCurrency(): String = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
package dev.ornelas.bankapp.commons

import java.text.NumberFormat
import java.util.*


private val currencyFormatter by lazy { NumberFormat.getCurrencyInstance(Locale("pt", "BR")) }

fun Double?.formatToCurrency(): String? = currencyFormatter.format(this)
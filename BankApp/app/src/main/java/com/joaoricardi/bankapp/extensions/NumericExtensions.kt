package com.joaoricardi.bankapp.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

val ptBRLocale = Locale("pt", "BR")

val noSymbolFormatter =
    (NumberFormat.getCurrencyInstance(ptBRLocale) as DecimalFormat).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        positivePrefix = ""
        negativePrefix = "-"
        decimalFormatSymbols.currencySymbol = ""
    }

fun Double.asBRL(includeCurrency: Boolean = false): String = if (includeCurrency) "R$ " + noSymbolFormatter.format(this) else noSymbolFormatter.format(this)
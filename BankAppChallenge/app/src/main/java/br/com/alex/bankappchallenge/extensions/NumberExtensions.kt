package br.com.alex.bankappchallenge.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

val ptBRLocale = Locale("pt", "BR")

val noSymbolFormatter =
    (NumberFormat.getCurrencyInstance(ptBRLocale) as DecimalFormat).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        positivePrefix = ""
        negativePrefix = "-"
        decimalFormatSymbols.currencySymbol = ""
    }

fun Double.asBRL() = "R$ " + noSymbolFormatter.format(this)

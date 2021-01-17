package com.jeanjnap.domain.util.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

private const val MONETARY_FORMAT = "#,##0"
private const val DOT = "."
private const val SEMI_COLON = ";"
private const val ZERO_VALUE = 0
private const val TWO_VALUE = 2

fun BigDecimal.formatCurrency(useSymbol: Boolean = true, decimalPlaces: Int = TWO_VALUE): String {
    val locale = Locale("pt", "BR")
    val format = MONETARY_FORMAT + DOT + ZERO_VALUE.toString().repeat(decimalPlaces) + SEMI_COLON
    val symbol = NumberFormat.getCurrencyInstance(locale).currency?.getSymbol(locale)
    val pattern: String = if (useSymbol) "$symbol $format" else format
    val df = DecimalFormat(pattern, DecimalFormatSymbols.getInstance(locale))
    return df.format(this)
}
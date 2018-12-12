package br.com.rphmelo.bankapp.common.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.formatMoney(): String {
    val pattern = "R$ #,##0.00"
    val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
    otherSymbols.decimalSeparator = ','
    otherSymbols.groupingSeparator = '.'
    val formatter = DecimalFormat(pattern, otherSymbols)
    return formatter.format(this)
}
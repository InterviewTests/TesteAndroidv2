package br.com.mdr.testeandroid.extensions

import java.text.NumberFormat
import java.util.*

/**
 * @author Marlon D. Rocha
 * @since 07/07/20
 */

fun Double.formattedCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}
package com.felipemsa.idletime.helper

import java.text.NumberFormat
import java.util.*

fun Double.formatToCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this).replace("R$", "R$ ")
}

fun String.mask(mask: String): String {
    var stringWithMask = ""

    var i = 0
    for (m: Char in mask.toCharArray()) {
        if (m != '#') {
            stringWithMask += m
            continue
        }
        try {
            stringWithMask += this[i]
        } catch (e: Exception) {
            break
        }
        i++
    }

    return stringWithMask
}
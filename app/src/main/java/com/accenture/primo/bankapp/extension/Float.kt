package com.accenture.primo.bankapp.extension

import java.text.NumberFormat
import java.util.Locale

fun Float.toMoney() : String {
    val nbfFormat = NumberFormat.getCurrencyInstance(Locale("pt","BR"))
    return nbfFormat.format(this)//.replace("R$", "R$ ")
}

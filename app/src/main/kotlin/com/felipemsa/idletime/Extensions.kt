package com.felipemsa.idletime

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun String.isValidPassword(): Boolean {

    val conditionUppercase = "[A-Z]".toRegex()
    val conditionNumber = "[0-9]".toRegex()
    val conditionEspecial = "[^a-zA-Z0-9]".toRegex()

    if (!conditionUppercase.containsMatchIn(this)) return false
    if (!conditionNumber.containsMatchIn(this)) return false
    if (!conditionEspecial.containsMatchIn(this)) return false

    return true
}
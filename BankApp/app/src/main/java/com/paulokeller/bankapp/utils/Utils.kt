package com.paulokeller.bankapp.utils

import java.text.NumberFormat
import java.util.*

object Utils {
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    fun formatCurrency(amount: Float) : String {
        return numberFormat.format(amount)
    }

    fun validatePassword(value: String): Boolean {
        val regex = """^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\${'$'}%^&*])""".toRegex();
        return regex.containsMatchIn(value)
    }
}
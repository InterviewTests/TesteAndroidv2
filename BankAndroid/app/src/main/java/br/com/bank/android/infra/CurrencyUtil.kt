package br.com.bank.android.infra

import java.text.NumberFormat
import java.util.*

object CurrencyUtil {

    fun toCurrencyValue(value: Double): String {
        val ptBr = Locale("pt", "BR")
        return NumberFormat.getCurrencyInstance(ptBr).format(value)
    }
}
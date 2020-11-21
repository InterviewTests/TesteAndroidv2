package com.example.bankapp.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    fun converterDataddMMyyyy(data: String): String {

        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd/MM/yyyy")

        return formatter.format(parser.parse(data))
    }

    fun convertToCurrency(valor: Double): String {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.maximumFractionDigits = 2
        formatter.currency = Currency.getInstance(Locale("pt", "BR"))

        return formatter.format(valor)
    }
}
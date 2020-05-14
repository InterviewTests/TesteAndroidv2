package com.example.testeandroideveris.feature.util

import java.text.SimpleDateFormat
import java.util.*

object BankUtil {
    private var DATE_FORMAT = "dd/MM/yyyy"

    fun formatAccountNumber(agency: String, bankAccount: String) = String.format("%s / %s", agency, bankAccount)

    fun formatAmountValue(amount: Double) = String.format("R$ %,.2f", amount)

    fun formatDate(strDate: String): String {
        val initialFormat = SimpleDateFormat(
            "yyyy-MM-dd",
            getDefaultLocale()
        )
        val format = SimpleDateFormat(
            DATE_FORMAT,
            getDefaultLocale()
        )
        val date: Date = initialFormat.parse(strDate)
        return format.format(date)
    }

    private fun getDefaultLocale(): Locale{
        return Locale("pt", "BR")
    }
}


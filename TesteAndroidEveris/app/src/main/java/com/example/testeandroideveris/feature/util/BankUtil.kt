package com.example.testeandroideveris.feature.util

import java.text.SimpleDateFormat
import java.util.*

object BankUtil {
    private var DATE_FORMAT = "dd/MM/yyyy"
    val weightCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)

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

    fun digitCalc(str: String, weight: IntArray): Int {
        var sum = 0
        var index = str.length - 1
        var digit: Int
        while (index >= 0) {
            digit = str.substring(index, index + 1).toInt()
            sum += digit * weight[weight.size - str.length + index]
            index--
        }
        sum = 11 - sum % 11
        return if (sum > 9) 0 else sum
    }
}


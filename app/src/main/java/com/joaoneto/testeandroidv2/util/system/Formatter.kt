package com.joaoneto.testeandroidv2.util.system

import java.text.NumberFormat

class Formatter {

    fun formatToCurrency(value: Double): String{

        return NumberFormat.getCurrencyInstance().format(value)

    }

    fun formatToDate(date: String): String{

        return "${date.substring(8, 9)}/${date.substring(
            5,
            6
        )}/${date.substring(0, 3)}"
    }

    fun formatAccountNumber(agency: String, bankAccount: String): String{
        return "${bankAccount}/${agency.substring(
            0,
            7
        )}-${agency.substring(8)}"
    }
}
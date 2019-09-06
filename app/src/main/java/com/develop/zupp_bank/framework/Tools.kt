package com.develop.zupp_bank.framework

import java.text.DecimalFormat
import java.util.*

object Tools {

    fun formatForBrazilianCurrency(value: Float) : String {
        val brazilianFormat = DecimalFormat
            .getCurrencyInstance(Locale("pt", "br"))
        return brazilianFormat.format(value)
    }

    fun formatDate(value: String): String{

        val dd: String
        val mm: String
        val yy: String

        dd = value.substring(8,10)
        mm = value.substring(5,7)
        yy = value.substring(0,4)

        val date = dd+"/"+mm+"/"+yy

        return date

    }

    fun isEmailValid(emailAux: String): Boolean {
        return emailAux.contains("@") && !emailAux.contains(" ")
    }

}
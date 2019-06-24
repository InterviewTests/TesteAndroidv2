package br.com.vinicius.bankapp.internal

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.text.NumberFormat

object Formation {

    @JvmStatic
    fun stringToDate(string: String?) = string?.let {
        LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @JvmStatic
    fun dateToString(dateTime: LocalDate?) = dateTime?.format(DateTimeFormatter.ISO_LOCAL_DATE)

    fun stringToStringPattern(string: String): String =
        DateTimeFormatter.ofPattern("d/MM/yyyy").format(stringToDate(string))


    @JvmStatic
    fun currencyFormat(value: Double): String = NumberFormat.getCurrencyInstance().format(value)
        .replace("(", "").replace(")", "")

    @JvmStatic
    fun accountFormat(value: String):String {
        val str = StringBuilder(value).reverse()
        return StringBuilder("${str.substring(0,1)}-${str.substring(1,7)}.${str.substring(7)}").reverse().toString()
    }

}


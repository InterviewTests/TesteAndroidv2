package br.com.vinicius.bankapp.internal

import android.util.Patterns
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.text.NumberFormat
import java.util.regex.Pattern

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
        if(value == null) return ""
        val str = StringBuilder(value).reverse()
        return StringBuilder("${str.substring(0,1)}-${str.substring(1,7)}.${str.substring(7)}").reverse().toString()
    }

    @JvmStatic
    fun emailFormat(value: String): Boolean {
        if(value == null) return false
        val patterns = Pattern.compile(EMAIL_PATTERN)
        val matcher = patterns.matcher(value)
        val boo = matcher.matches()
        return Pattern.compile(EMAIL_PATTERN).matcher(value).matches()
    }


}


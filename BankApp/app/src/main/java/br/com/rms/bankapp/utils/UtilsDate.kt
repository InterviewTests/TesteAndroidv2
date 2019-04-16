package br.com.rms.bankapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat

class UtilsDate {

    companion object {

        fun formatSimpleDate(date: String): String {
            var dateFormatted = ""
            if (date.isNotEmpty()) {
                dateFormatted = try {
                    val locale = java.util.Locale.getDefault()
                    val parser = SimpleDateFormat("yyyy-MM-dd", locale)
                    val formatter = SimpleDateFormat("dd/MM/yyyy", locale)
                    formatter.format(parser.parse(date))
                } catch (e: ParseException){
                    ""
                }
            }
            return dateFormatted
        }
    }
}
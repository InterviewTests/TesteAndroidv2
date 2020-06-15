package com.qintess.santanderapp.helper

import android.annotation.SuppressLint
import java.math.RoundingMode
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object Formatter {
    fun formatMoney(value: Double): String {
        val formatter = getMoneyFormatter()
        return formatter.format(value)
    }

    fun formatDate(strDate: String): String {
        val toDateFormatter = getDateFormatterStringToDate()
        val toStringFormatter = getDateFormatterDateToString()

        val date = toDateFormatter.parse(strDate)
        return toStringFormatter.format(date)
    }

    private fun getMoneyFormatter(): DecimalFormat {
        val nf = NumberFormat.getInstance(Locale.US) as DecimalFormat
        nf.roundingMode = RoundingMode.HALF_UP
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2

        val symbols = nf.decimalFormatSymbols
        symbols.groupingSeparator = '.'
        symbols.decimalSeparator = ','
        nf.decimalFormatSymbols = symbols

        nf.positivePrefix = "R$ "
        nf.negativePrefix = "R$ -"

        return nf
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateFormatterStringToDate(): DateFormat {
        return SimpleDateFormat("yyyy-MM-dd")
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateFormatterDateToString(): DateFormat {
        return SimpleDateFormat("dd/MM/yyyy")
    }
}
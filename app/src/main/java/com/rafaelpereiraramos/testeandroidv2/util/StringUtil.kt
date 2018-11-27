package com.rafaelpereiraramos.testeandroidv2.util

import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rafael P. Ramos on 26/11/2018.
 */
class StringUtil {
    companion object {

        fun applyAgencyMask(agency: String?): String {
            if (agency.isNullOrEmpty()) return  ""

            return buildString {
                append(agency)
                insert(2, ".")
                val lastCharPos = length
                insert(lastCharPos - 1, "-")
            }
        }

        fun applyMoneyMask(value: Float) = applyMoneyMask(value.toDouble())

        fun applyMoneyMask(value: Double): String {
            val formatter = DecimalFormat.getInstance() as DecimalFormat
            formatter.maximumFractionDigits = 2
            formatter.minimumFractionDigits = 2

            val strValue = formatter.format(value)
            val currencySymbol = Currency.getInstance(Locale.getDefault()).symbol

            return "$currencySymbol $strValue"
        }

        fun applyShortDateFormat(date: Date): String {
            val formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()) as SimpleDateFormat

            return formatter.format(date)
        }
    }
}
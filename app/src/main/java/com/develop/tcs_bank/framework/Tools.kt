package com.develop.tcs_bank.framework

import android.text.TextUtils
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Pattern

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
        return emailAux.contains("@") && emailAux.contains(".") && !emailAux.contains(" ")
    }

    fun isCPF(document: String): Boolean {
        if (TextUtils.isEmpty(document)) return false

        val numbers = arrayListOf<Int>()

        document.filter { it.isDigit() }.forEach {
            numbers.add(it.toString().toInt())
        }

        if (numbers.size != 11) return false

        (0..9).forEach { n ->
            val digits = arrayListOf<Int>()
            (0..10).forEach { digits.add(n) }
            if (numbers == digits) return false
        }

        val dv1 = ((0..8).sumBy { (it + 1) * numbers[it] }).rem(11).let {
            if (it >= 10) 0 else it
        }

        val dv2 = ((0..8).sumBy { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
            if (it >= 10) 0 else it
        }

        return numbers[9] == dv1 && numbers[10] == dv2
    }

    fun isPasswordValidate(document: String) : Boolean{

        val reg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).{4,}"
        val ret: Boolean
        ret = Pattern.compile(reg).matcher(document).matches()
        if (!ret) {
            return false
        }
        else{
           return true
        }

    }


}
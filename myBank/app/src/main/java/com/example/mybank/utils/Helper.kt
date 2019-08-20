package com.example.mybank.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import java.text.SimpleDateFormat
import android.text.method.TextKeyListener.clear
import java.util.regex.Pattern


class Helper {
    companion object {
        fun formatDate(date: String) : String {
            val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val dateFromServer = dateFormat.parse(date)
            return SimpleDateFormat("dd/MM/yyyy").format(dateFromServer)
        }

        fun roundDouble(value: Double): String {
            return String.format("%.2f", value)
        }

        fun formatAccount(account: String) : String {
            val point = account.subSequence(0, 2).toString()
            val dash = account.subSequence(2, 8).toString()
            val last = account.subSequence(8,9).toString()
            return "$point.$dash-$last"
        }

        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isCPF(document: String): Boolean {
            if (document == "" || document == null) return false

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

        fun validatePassword(password: String) : Boolean {
            val PASSWORD_REGEX = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\S+${'$'}).{6,}""".toRegex()
            return PASSWORD_REGEX.matches(password)
        }

    }
}
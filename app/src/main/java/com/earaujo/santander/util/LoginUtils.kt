package com.earaujo.santander.util

import java.util.regex.Pattern

class LoginUtils {
    companion object {
        private val CpfWeight = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)

        private fun calculateDigit(str: String, weight: IntArray): Int {
            var sum = 0
            var i = str.length - 1
            var digit: Int
            while (i >= 0) {
                digit = Integer.parseInt(str.substring(i, i + 1))
                sum += digit * weight[weight.size - str.length + i]
                i--
            }
            sum = 11 - sum % 11
            return if (sum > 9) 0 else sum
        }

        fun onlyNumber(s: String) = s.replace("[^\\d]".toRegex(), "")

        private fun isValidCpf(cpf: String): Boolean {
            val filteredCpf = onlyNumber(cpf)
            if (filteredCpf.length != 11) return false
            val digit1 = calculateDigit(filteredCpf.substring(0, 9), CpfWeight)
            val digit2 = calculateDigit(filteredCpf.substring(0, 9) + digit1, CpfWeight)
            return filteredCpf == filteredCpf.substring(0, 9) + digit1.toString() + digit2.toString()
        }

        private fun isValidEmail(s: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()
        }

        fun isValidUsername(s: String): Boolean =
            isValidCpf(s) or isValidEmail(s)

        private fun hasUpperCase(s: String): Boolean = Pattern.compile("[A-Z ]").matcher(s).find()

        private fun hasSpecialChar(s: String): Boolean =
            Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher(s).find()

        private fun hasAlphaNumeric(s: String): Boolean =
            Pattern.compile("[a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher(s).find()

        fun isValidPassword(s: String): Boolean =
            hasUpperCase(s) and hasSpecialChar(s) and hasAlphaNumeric(s)
    }
}
package com.santander.app.core.util.validation

import java.util.regex.Pattern

private val VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
private val VALID_PASSWORD_REGEX = Pattern.compile("(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%!\\-_?&])")


fun String?.isValidPassword() =  VALID_PASSWORD_REGEX.matcher(this.orEmpty()).find()

fun String?.isValidEmail() = VALID_EMAIL_ADDRESS_REGEX.matcher(this.orEmpty()).find()

fun String?.isValidCPF(): Boolean {

    val pesoCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)

    fun calcDigit(str: String, peso: IntArray): Int {
        var sun = 0
        var index = str.length - 1
        var digit: Int
        while (index >= 0) {
            digit = Integer.parseInt(str.substring(index, index + 1))
            sun += digit * peso[peso.size - str.length + index]
            index--
        }
        sun = 11 - sun % 11
        return if (sun > 9) 0 else sun
    }

    return when (this) {
        "00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
        "55555555555", "66666666666", "77777777777", "88888888888", "99999999999" -> false
        else -> {
            if (this == null || this.length != 11) return false

            val digit1 = calcDigit(this.substring(0, 9), pesoCPF)
            val digit2 = calcDigit(this.substring(0, 9) + digit1, pesoCPF)
            this == this.substring(0, 9) + digit1.toString() + digit2.toString()
        }
    }
}

fun String.extractNumbers() = this.replace(Regex("\\D+"), "")
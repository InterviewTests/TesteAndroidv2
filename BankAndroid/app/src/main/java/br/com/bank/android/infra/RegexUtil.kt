package br.com.bank.android.infra

import java.util.regex.Pattern

object RegexUtil {

    private val REGEX_PASSWORD = "(?=.*[a-z0-9])(?=.*[A-Z])(?=.*\\W+).*\$"

    fun isValidPassword(password: String?): Boolean {
        if (password == null) return false
        val pattern = Pattern.compile(REGEX_PASSWORD)
        return pattern.matcher(password).matches()
    }
}
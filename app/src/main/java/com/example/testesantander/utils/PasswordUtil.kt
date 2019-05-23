package com.example.testesantander.utils

import java.util.regex.Matcher
import java.util.regex.Pattern
import android.R.attr.password



class PasswordUtil {
    companion object {
        fun isValidPassword(password: String?) : Boolean {
            val pattern: Pattern
            val matcher: Matcher

            val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!.])(?=\\S+$).{4,}$"

            pattern = Pattern.compile(PASSWORD_PATTERN)
            matcher = pattern.matcher(password)

            return matcher.matches()
        }
    }
}
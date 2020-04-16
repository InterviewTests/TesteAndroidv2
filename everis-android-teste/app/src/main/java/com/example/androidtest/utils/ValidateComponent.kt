package com.example.androidtest.utils

import android.util.Patterns
import com.example.androidtest.utils.Constants.CPF_MASK
import java.util.regex.Pattern

object ValidateComponent {

    fun isValidEmail(text : String): Boolean
            = text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(text).matches()

    fun isValidCpf(text: String): Boolean {
        var result = false
        if (text.isNotEmpty()) {
            if (text.length == CPF_MASK.length) {
                result = true
            }
        }
        return result
    }

    fun isValidPassword(text: String): String {
        val result = ""

        if (text.isEmpty()) {
            return "A senha deve ter um caractere especial, maiúsculo, minúsculo e numérico !!"
        }

        val specailCharPatten: Pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val upperCasePatten: Pattern = Pattern.compile("[A-Z ]")
        val lowerCasePatten: Pattern = Pattern.compile("[a-z ]")
        val digitCasePatten: Pattern = Pattern.compile("[0-9 ]")

        if (!specailCharPatten.matcher(text).find()) {
            return "A senha deve ter pelo menos um caractere especial !!"
        }
        if (!upperCasePatten.matcher(text).find()) {
            return "A senha deve ter pelo menos um caractere maiúsculo !!"
        }
        if (!lowerCasePatten.matcher(text).find()) {
            return "A senha deve ter pelo menos um caractere minúsculo !!"
        }
        if (!digitCasePatten.matcher(text).find()) {
            return "A senha deve ter pelo menos um caractere numérico !!"
        }

        return result
    }
}
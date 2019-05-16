package com.example.testeandroidv2.util

import android.content.Context
import br.com.concrete.canarinho.validator.Validador
import com.example.testeandroidv2.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Utils {

    fun showAlertDialog(context: Context, message: String) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.error_title)
            .setMessage(message)
            .setPositiveButton(R.string.ok, null)
            .show()
    }

    fun checkLoginData(user: String, password: String): Boolean {
        val validUser = when {
            android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches() -> {
                true
            }
            Validador.CPF.ehValido(user) -> {
                true
            }
            else -> {
                false
            }
        }

        var hasNumber = false
        var hasUpperCaseLetter = false
        var hasSymbol = false

        for (char in password) {
            when (char) {
                in '0'..'9' -> hasNumber = true
                in 'A'..'Z' -> hasUpperCaseLetter = true
                else -> hasSymbol = true
            }
        }

        val validPassword = hasNumber && hasUpperCaseLetter && hasSymbol

        return validUser && validPassword
    }
}
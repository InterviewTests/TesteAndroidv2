package com.example.testeandroidv2

import br.com.concrete.canarinho.validator.Validador

class Utils {

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

        val validPassword = password.matches(Regex("[a-zA-Z]+")) &&
                password.matches(Regex("[0-9]+")) &&
                password.matches(Regex("['\"!@#\$%&*()]+"))

        return validUser && validPassword
    }
}
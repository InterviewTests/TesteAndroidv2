package com.jfgjunior.bankapp.data.models

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

data class UserCredentials(
    var user: String = "",
    var password: String = ""
) {

    fun isUserValid(): Boolean {
        return when {
            PatternsCompat.EMAIL_ADDRESS.matcher(user).matches() -> true
            validateCPF(user) -> true
            else -> false
        }
    }

    fun isEmpty() = user.isEmpty() || password.isEmpty()

    /**
     *  Validates if the password has:
     *  - At least one capital letter (?=.*[A-Z])
     *  - At least one alphanumerical character (?=.*[a-z0-9])
     *  - At least one special non alphanumerical (special character) (?=.*([^A-Za-z0-9])
     *
     *  @return true if the string matches with the pattern, false otherwise.
     * */
    fun isPasswordValid(): Boolean {
        return Pattern.compile("^(?=.*[a-z0-9])(?=.*[A-Z])(?=.*([^A-Za-z0-9]))[!-~]{3,}$")
            .matcher(password).matches()
    }

    /**
     *  Validates if the [cpf] has 11 digits, ignoring the normal CPF formatting.
     *
     *  @return true if has 11 digits, false otherwise.
     */
    private fun validateCPF(cpf: String): Boolean {
        // TODO: do the calculation to verify if it's a valid CPF
        return Pattern.compile("([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2})")
            .matcher(cpf).matches()
    }
}
package br.com.alex.bankappchallenge.utils

class PasswordValidator : PasswordValidatorContract {

    private val conditions = listOf(
        "[^a-zA-Z 0-9]",
        "[0-9]",
        "[A-Z]"
    )

    override fun validatePassword(password: String): Boolean {
        conditions.forEach {
            if (!password.contains(Regex(it)))
                return false
        }
        return true
    }
}

interface PasswordValidatorContract {
    fun validatePassword(password: String): Boolean
}
package com.jeanjnap.bankapp.ui.login

class LoginForm {

    var user: String = EMPTY_TEXT
    var pass: String = EMPTY_TEXT

    fun isValidPassword() = PASSWORD_REGEX.matches(pass)
    fun isValidUsername() = user.isNotEmpty()
    fun isFormValid() = isValidPassword() && isValidUsername()

    companion object {
        private const val EMPTY_TEXT = ""
        private val PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[!@#\$%&*])(?=.*[a-z0-9]).{3,}\$".toRegex()

    }
}
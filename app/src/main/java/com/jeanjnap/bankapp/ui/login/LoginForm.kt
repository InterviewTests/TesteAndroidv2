package com.jeanjnap.bankapp.ui.login

import com.jeanjnap.domain.util.extensions.isCPF

class LoginForm {

    var user: String = EMPTY_TEXT
    var pass: String = EMPTY_TEXT

    fun isValidPassword() = PASSWORD_REGEX.matches(pass)
    fun isValidUsername() = EMAIL_REGEX.matches(user) || user.isCPF()
    fun isFormValid() = isValidPassword() && isValidUsername()

    companion object {
        private const val EMPTY_TEXT = ""
        private val PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[!@#\$%&*])(?=.*[a-z0-9]).{3,}\$".toRegex()
        private val EMAIL_REGEX = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$".toRegex()
    }
}

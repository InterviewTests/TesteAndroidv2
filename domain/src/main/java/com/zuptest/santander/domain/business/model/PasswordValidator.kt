package com.zuptest.santander.domain.business.model

object PasswordValidator {

    fun isValid(password: String) : Boolean {
        return password.matches("^(?=.*[A-Z])(?=.*[!@#\$&*])(?=.*[0-9])(?=.*[a-z]).{3,}\$".toRegex())
    }
}
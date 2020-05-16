package br.com.crmm.bankapplication.util


class PasswordUtil {

    fun isValid(password: String): Boolean{
        return when {
            !".*[A-Z].*".toRegex().matches(password) -> false
            !".*[a-z0-9].*".toRegex().matches(password) -> false
            !".*[~!@#\$%^&*()\\-_=+|\\[{\\]};:'\",<.>/?].*".toRegex().matches(password) -> false
            else -> true
        }
    }
}
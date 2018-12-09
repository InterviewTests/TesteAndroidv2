package br.com.rphmelo.bankapp.presentation.login

import br.com.rphmelo.bankapp.extensions.isEmail
import br.com.rphmelo.bankapp.extensions.isValidCPF
import br.com.rphmelo.bankapp.extensions.isValidPassword
import br.com.rphmelo.bankapp.presentation.login.models.OnLoginFinishedListener

class LoginInteractor {

    fun login(user: String, password: String, listener: OnLoginFinishedListener) {
        when {
            user.isEmpty() -> listener.onUserEmptyError()
            password.isEmpty() -> listener.onPasswordEmptyError()
            !validUser(user) -> listener.onUserInvalidError()
            !password.isValidPassword() -> listener.onPasswordInvalidError()
            else -> listener.onSuccess()
        }
    }

    private fun validUser(user: String): Boolean {
        return user.isEmail() || user.isValidCPF()
    }

}
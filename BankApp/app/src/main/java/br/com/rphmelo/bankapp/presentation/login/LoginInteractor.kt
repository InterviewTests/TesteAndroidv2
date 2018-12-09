package br.com.rphmelo.bankapp.presentation.login

import br.com.rphmelo.bankapp.extensions.isEmail
import br.com.rphmelo.bankapp.extensions.isValidPassword
import br.com.rphmelo.bankapp.presentation.login.models.OnLoginFinishedListener

class LoginInteractor {

    fun login(user: String, password: String, listener: OnLoginFinishedListener) {
        when {
            user.isEmpty() -> listener.onUserEmptyError()
            password.isEmpty() -> listener.onPasswordEmptyError()
            !user.isEmail() -> listener.onUserInvalidError()
            !password.isValidPassword() -> listener.onPasswordInvalidError()
            else -> listener.onSuccess()
        }
    }

}
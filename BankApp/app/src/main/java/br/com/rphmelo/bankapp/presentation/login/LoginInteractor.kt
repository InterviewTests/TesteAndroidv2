package br.com.rphmelo.bankapp.presentation.login

import br.com.rphmelo.bankapp.presentation.login.models.OnLoginFinishedListener

class LoginInteractor {

    fun login(user: String, password: String, listener: OnLoginFinishedListener) {
        when {
            user.isEmpty() -> listener.onUserError()
            password.isEmpty() -> listener.onPasswordError()
            else -> listener.onSuccess()
        }
    }

}
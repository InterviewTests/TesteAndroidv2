package com.lucianogiardino.bankapp.login.domain.usecase

import com.lucianogiardino.bankapp.login.LoginContract

class ValidateUseCase {

    fun execute(listener: LoginContract.Presenter.OnValidateResponse, username:String, password: String) {

        var regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+$).{1,}".toRegex()

        if (regex.matches(password)) {
            listener.onValidateResponseSuccess(username,password)
        } else {
            listener.onValidateResponseFailed("Formato da senha inválido")
        }

    }
}
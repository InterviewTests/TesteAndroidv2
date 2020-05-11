package com.lucianogiardino.bankapp.domain.usecase

import com.lucianogiardino.bankapp.presentation.login.LoginContract

class ValidatePasswordUseCase : LoginContract.UseCase.ValidatePassword{

    override fun execute(listener: LoginContract.Presenter.OnValidatePasswordResponse, password: String) {

        var regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+$).{1,}".toRegex()

        if (regex.matches(password)) {
            listener.onValidatePasswordResponseSuccess(true)
        } else {
            listener.onValidatePasswordResponseFailed("Formato da senha inválido")
        }

    }
}
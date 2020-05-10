package com.lucianogiardino.bankapp.domain.usecase

import com.lucianogiardino.bankapp.presentation.login.LoginContract

class ValidateUserUseCase : LoginContract.UseCase.ValidateUser{

    override fun execute(listener: LoginContract.Presenter.OnValidateUserResponse, username:String, password: String) {

        var regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+$).{1,}".toRegex()

        if (regex.matches(password)) {
            listener.onValidateUserResponseSuccess(username,password)
        } else {
            listener.onValidateUserResponseFailed("Formato da senha inválido")
        }

    }
}
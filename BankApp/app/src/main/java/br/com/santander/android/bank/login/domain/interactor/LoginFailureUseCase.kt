package br.com.santander.android.bank.login.domain.interactor

import br.com.santander.android.bank.core.exceptions.FailureUseCase

sealed class LoginFailureUseCase {

    object EmptyUser: FailureUseCase()
    object EmptyPassword: FailureUseCase()
    object MalformattedPassword: FailureUseCase()

}
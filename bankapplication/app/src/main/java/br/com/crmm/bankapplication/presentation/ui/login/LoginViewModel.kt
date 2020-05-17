package br.com.crmm.bankapplication.presentation.ui.login

import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import br.com.crmm.bankapplication.presentation.ui.common.AbstractViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): AbstractViewModel() {

    fun performLogin(){
        loginUseCase.execute()
    }
}
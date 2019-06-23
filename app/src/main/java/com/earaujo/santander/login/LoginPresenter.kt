package com.earaujo.santander.login

import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.models.UserAccountModel
import java.lang.ref.WeakReference

class LoginPresenter : LoginPresenterInput {

    lateinit var loginActivityInput: WeakReference<LoginActivityInput>

    override fun presentLoginResponse(loginResponse: LoginResponse) {
        loginActivityInput.get()?.loginCallback(LoginActivityModel(UserAccountModel(
                1,
                "Eduardo Nunes",
                "1234",
                "4321",
                15000.0
            )))
    }

    override fun presentErrorMessage(errorMessage: String) {
        loginActivityInput.get()?.displayErrorMessage(errorMessage)
    }
}

interface LoginPresenterInput {
    fun presentLoginResponse(loginResponse: LoginResponse)
    fun presentErrorMessage(errorMessage: String)
}
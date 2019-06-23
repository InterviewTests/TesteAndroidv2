package com.earaujo.santander.login

import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.models.UserAccountModel
import java.lang.ref.WeakReference

class LoginPresenter : LoginPresenterInput {

    lateinit var loginActivityInput: WeakReference<LoginActivityInput>

    override fun presentLoginResponse(loginResponse: LoginResponse) {
        loginActivityInput.get()?.loginCallback(loginResponse.userAccountModel)
    }

    override fun presentErrorMessage(errorMessage: String) {
        loginActivityInput.get()?.displayErrorMessage(errorMessage)
    }
}

interface LoginPresenterInput {
    fun presentLoginResponse(loginResponse: LoginResponse)
    fun presentErrorMessage(errorMessage: String)
}
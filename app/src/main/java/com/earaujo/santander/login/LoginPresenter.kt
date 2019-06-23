package com.earaujo.santander.login

import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.models.UserAccountModel
import java.lang.ref.WeakReference

class LoginPresenter : LoginPresenterInput {

    lateinit var loginActivityInput: WeakReference<LoginActivityInput>

    override fun presentLoading() {
        loginActivityInput.get()?.loginCallback(Resource.loading(null))
    }

    override fun presentLoginResponse(loginResponse: LoginResponse) {
        loginActivityInput.get()?.loginCallback(Resource.success(loginResponse.userAccountModel))
    }

    override fun presentErrorMessage(errorMessage: String) {
        loginActivityInput.get()?.loginCallback(Resource.error(errorMessage, null))
    }

    override fun presentSetUserName(userName: String) {
        loginActivityInput.get()?.displayUserName(userName)
    }
}

interface LoginPresenterInput {
    fun presentLoading()
    fun presentLoginResponse(loginResponse: LoginResponse)
    fun presentErrorMessage(errorMessage: String)
    fun presentSetUserName(userName: String)
}
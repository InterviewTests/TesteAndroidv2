package com.earaujo.santander.login

import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginResponse
import java.lang.ref.WeakReference

class LoginPresenter : LoginPresenterInput {

    lateinit var loginActivityInput: WeakReference<LoginActivityInput>

    override fun presentLoginResponse(loginResponse: Resource<LoginResponse>) {
        loginActivityInput.get()?.loginCallback(loginResponse)
    }

    override fun presentSetUserName(userName: String) {
        loginActivityInput.get()?.displayUserName(userName)
    }
}

interface LoginPresenterInput {
    fun presentLoginResponse(loginResponse: Resource<LoginResponse>)
    fun presentSetUserName(userName: String)
}
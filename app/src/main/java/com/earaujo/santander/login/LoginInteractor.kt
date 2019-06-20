package com.earaujo.santander.login

import com.earaujo.santander.repository.LoginRepositoryCallback
import com.earaujo.santander.repository.LoginRepositoryFakeImpl
import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.LoginResponse

class LoginInteractor : LoginInteractorInput {

    lateinit var loginPresenterInput: LoginPresenterInput
    val loginRepository = LoginRepositoryFakeImpl()

    override fun performLogin(loginRequest: LoginRequest) {
        loginRepository.fetchLogin(loginRequest, object : LoginRepositoryCallback {
            override fun onData(loginResponse: LoginResponse) {
                loginPresenterInput.presentLoginResponse(loginResponse)
            }
        })
    }
}

interface LoginInteractorInput {
    fun performLogin(loginRequest: LoginRequest)
}
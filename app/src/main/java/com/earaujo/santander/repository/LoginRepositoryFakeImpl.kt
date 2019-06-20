package com.earaujo.santander.repository

import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.models.UserAccountModel

class LoginRepositoryFakeImpl : LoginRepository {

    override fun fetchLogin(loginRequest: LoginRequest, loginRepositoryCallback: LoginRepositoryCallback) {
        loginRepositoryCallback.onData(LoginResponse(
            UserAccountModel(1, "Eduardo", "123", "0123", 1.0),
            null
        ))
    }
}
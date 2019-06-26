package com.earaujo.santander.repository

import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.rest.ApiUtil

class LoginRepositoryImpl : LoginRepository {

    override fun fetchLogin(loginRequest: LoginRequest, loginRepositoryCallback: LoginRepositoryCallback) {
        ApiUtil().login(loginRequest.user, loginRequest.password, object: ApiUtil.LoginCallback {
            override fun onResponse(loginResponse: Resource<LoginResponse>) {
                loginRepositoryCallback.onData(loginResponse)
            }
        })
    }

}
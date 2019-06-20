package com.earaujo.santander.repository

import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.LoginResponse

interface LoginRepository {
    fun fetchLogin(loginRequest: LoginRequest, loginRepositoryCallback: LoginRepositoryCallback)
}

interface LoginRepositoryCallback {
    fun onData(loginResponse: LoginResponse)
}
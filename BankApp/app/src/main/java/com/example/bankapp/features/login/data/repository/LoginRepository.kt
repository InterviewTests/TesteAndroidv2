package com.example.bankapp.features.login.data.repository

import com.example.bankapp.features.login.data.service.LoginService
import com.example.bankapp.features.login.model.LoginRequest
import com.example.bankapp.features.login.model.LoginResponse
import io.reactivex.Single
import javax.inject.Inject

interface LoginRepository {

    fun getLogin(loginRequest: LoginRequest): Single<LoginResponse>

    class Impl @Inject constructor(
        private val service: LoginService
    ) : LoginRepository {
        override fun getLogin(loginRequest: LoginRequest): Single<LoginResponse> {
            return service.requestLogin(loginRequest)
        }
    }
}
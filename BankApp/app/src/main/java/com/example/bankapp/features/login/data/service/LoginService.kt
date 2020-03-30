package com.example.bankapp.features.login.data.service

import com.example.bankapp.features.login.model.LoginRequest
import com.example.bankapp.features.login.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    fun requestLogin(
        @Body
        loginRequest: LoginRequest
    ): Single<LoginResponse>
}


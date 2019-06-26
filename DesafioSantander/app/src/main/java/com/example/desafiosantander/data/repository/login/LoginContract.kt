package com.example.desafiosantander.data.repository.login

import com.example.desafiosantander.data.model.request.LoginRequest
import com.example.desafiosantander.data.model.response.LoginResponse
import io.reactivex.Flowable

interface LoginContract {

    fun login(loginRequest: LoginRequest): Flowable<LoginResponse>

}
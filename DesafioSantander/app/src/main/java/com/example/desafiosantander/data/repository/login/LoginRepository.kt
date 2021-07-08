package com.example.desafiosantander.data.repository.login

import com.example.desafiosantander.data.api.ApiService
import com.example.desafiosantander.data.model.request.LoginRequest
import com.example.desafiosantander.data.model.response.LoginResponse
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class LoginRepository(private val apiService: ApiService) : LoginContract {

    override fun login(loginRequest: LoginRequest): Flowable<LoginResponse> {
        return apiService.login(loginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
}
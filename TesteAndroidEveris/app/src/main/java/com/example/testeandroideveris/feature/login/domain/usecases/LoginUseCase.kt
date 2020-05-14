package com.example.testeandroideveris.feature.login.domain.usecases

import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository){

    suspend fun login(request: LoginRequestData) = repository.login(request)
}
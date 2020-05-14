package com.example.testeandroideveris.feature.login.domain.usecases

import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import com.example.testeandroideveris.feature.login.mappers.UserAccountMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class LoginUseCase(private val repository: LoginRepository){

    suspend fun login(request: LoginRequestData) = repository.login(request)
        .map { value: LoginResponseData -> UserAccountMapper.mapFrom(value.userAccount) }

    suspend fun getLastUser() = repository.getLastUser()
}
package com.example.testeandroideveris.feature.login.data.datasource

import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.api.LoginAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRemoteDataSourceImpl(private val api: LoginAPI) : LoginRemoteDataSource {

    override suspend fun login(loginRequest: LoginRequestData) = flow {
            emit(api.login(loginRequest))
        }.flowOn(Dispatchers.IO)
}
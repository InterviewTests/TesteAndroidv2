package com.example.testeandroideveris.feature.login.data.datasource

import com.example.testeandroideveris.data.api.RetrofitBuilder
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRemoteDataSourceImpl : LoginRemoteDataSource {

    override suspend fun login(loginRequest: LoginRequestData) = flow {
            emit(RetrofitBuilder.apiService.login(loginRequest))
        }.flowOn(Dispatchers.IO)
}
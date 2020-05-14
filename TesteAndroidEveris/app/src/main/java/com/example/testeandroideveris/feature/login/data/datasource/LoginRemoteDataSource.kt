package com.example.testeandroideveris.feature.login.data.datasource

import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import kotlinx.coroutines.flow.Flow

interface LoginRemoteDataSource {
    suspend fun login(loginRequest: LoginRequestData): Flow<LoginResponseData>
}
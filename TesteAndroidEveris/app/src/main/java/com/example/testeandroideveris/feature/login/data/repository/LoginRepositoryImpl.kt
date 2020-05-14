package com.example.testeandroideveris.feature.login.data.repository

import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import com.example.testeandroideveris.feature.login.data.datasource.LoginLocalDataSource
import com.example.testeandroideveris.feature.login.data.datasource.LoginRemoteDataSource
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

open class LoginRepositoryImpl(private val loginLocalDataSource: LoginLocalDataSource,
                               private val loginRemoteDataSource: LoginRemoteDataSource
)
    : LoginRepository {

    override suspend fun login(loginRequest: LoginRequestData): Flow<LoginResponseData> {
        saveUser(loginRequest.user)
        return loginRemoteDataSource.login(loginRequest)
    }
    override suspend fun getLastUser() = loginLocalDataSource.getUser()

    private fun saveUser(user: String) {
        loginLocalDataSource.saveUser(user)
    }
}
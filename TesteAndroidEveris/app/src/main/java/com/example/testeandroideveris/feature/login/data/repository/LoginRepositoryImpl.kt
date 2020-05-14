package com.example.testeandroideveris.feature.login.data.repository

import androidx.lifecycle.liveData
import com.example.testeandroideveris.data.api.RetrofitBuilder
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.datasource.LocalDataSource
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class LoginRepositoryImpl(private val localDataSource: LocalDataSource): LoginRepository {

    override suspend fun login(loginRequest: LoginRequestData) =  flow {
            saveUser(loginRequest.user)
            emit(RetrofitBuilder.apiService.login(loginRequest))
        }.flowOn(Dispatchers.IO)

    override suspend fun getLastUser() = localDataSource.getUser()

    private fun saveUser(user: String) {
        localDataSource.saveUser(user)
    }
}
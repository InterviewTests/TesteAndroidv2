package com.example.testeandroideveris.feature.login.data.repository

import com.example.testeandroideveris.data.api.RetrofitBuilder
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class LoginRepositoryImpl(): LoginRepository {

    override suspend fun login(loginRequest: LoginRequestData) =  flow {
            // exectute API call and map to UI object
            val fooList = RetrofitBuilder.apiService.login(loginRequest)
            // Emit the list to the stream
            emit(fooList)
        }.flowOn(Dispatchers.IO) // Use the IO thread for this Flow
}
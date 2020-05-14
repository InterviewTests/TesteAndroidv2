package com.example.testeandroideveris.feature.login.domain.repository

import androidx.lifecycle.LiveData
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.LoginResponseData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequestData): Flow<LoginResponseData>
    suspend fun getLastUser(): String?
}
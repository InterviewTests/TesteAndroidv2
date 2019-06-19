package com.farage.testeandroidv2.domain

import com.farage.testeandroidv2.datasource.model.UserResponse
import com.farage.testeandroidv2.util.ResultState

interface UserRepository {

    suspend fun userLogin(user: String, password: String): ResultState<UserResponse>
}
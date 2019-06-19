package com.farage.testeandroidv2.repository

import com.farage.testeandroidv2.datasource.UserDataSource
import com.farage.testeandroidv2.datasource.model.UserResponse
import com.farage.testeandroidv2.domain.UserRepository
import com.farage.testeandroidv2.util.ResultState

class UserRepositoryImpl constructor(private val userDataSource: UserDataSource) : UserRepository {

    override suspend fun userLogin(user: String, password: String): ResultState<UserResponse> {
        return ResultState.emptyData<UserResponse>()!!
    }
}
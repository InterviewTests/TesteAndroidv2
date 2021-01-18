package com.jeanjnap.data.source.remote

import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.UserAccount

interface BankRemoteDataSource {
    suspend fun login(username: String, password: String): Response<UserAccount>
    suspend fun getStatements(userId: Long?): Response<List<Statement>>
}
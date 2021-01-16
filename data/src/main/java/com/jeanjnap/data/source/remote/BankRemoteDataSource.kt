package com.jeanjnap.data.source.remote

import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.UserAccount

interface BankRemoteDataSource {
    suspend fun login(username: String, password: String): Response<UserAccount>
}
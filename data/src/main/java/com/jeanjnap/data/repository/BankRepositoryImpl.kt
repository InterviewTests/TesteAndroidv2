package com.jeanjnap.data.repository

import com.jeanjnap.data.source.remote.BankRemoteDataSource
import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.repository.BankRepository

class BankRepositoryImpl(
    private val bankRemoteDataSource: BankRemoteDataSource
): BankRepository {
    override suspend fun login(username: String, password: String): Response<UserAccount> {
        return bankRemoteDataSource.login(username, password)
    }
}
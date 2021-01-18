package com.jeanjnap.data.repository

import com.jeanjnap.data.source.local.BankLocalDataSource
import com.jeanjnap.data.source.remote.BankRemoteDataSource
import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.repository.BankRepository

class BankRepositoryImpl(
    private val bankRemoteDataSource: BankRemoteDataSource,
    private val bankLocalDataSource: BankLocalDataSource
) : BankRepository {
    override suspend fun login(username: String, password: String): Response<UserAccount> {
        return bankRemoteDataSource.login(username, password)
    }

    override suspend fun getStatements(userId: Long?): Response<List<Statement>> {
        return bankRemoteDataSource.getStatements(userId)
    }

    override fun saveEncryptedUser(user: String) {
        bankLocalDataSource.saveEncryptedUser(user)
    }

    override fun getUser(): String? {
        return bankLocalDataSource.getEncryptedUser()
    }
}
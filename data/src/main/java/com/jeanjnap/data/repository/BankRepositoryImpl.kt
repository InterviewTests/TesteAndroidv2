package com.jeanjnap.data.repository

import com.jeanjnap.data.source.local.BankLocalDataSource
import com.jeanjnap.data.source.remote.BankRemoteDataSource
import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.repository.BankRepository
import com.jeanjnap.infrastructure.crypto.RSACrypto

class BankRepositoryImpl(
    private val bankRemoteDataSource: BankRemoteDataSource,
    private val bankLocalDataSource: BankLocalDataSource,
    private val rsaCrypto: RSACrypto
): BankRepository {
    override suspend fun login(username: String, password: String): Response<UserAccount> {
        return bankRemoteDataSource.login(username, password).also {
            if (it is SuccessResponse) {
                bankLocalDataSource.saveEncryptedUser(rsaCrypto.encrypt(username))
            }
        }
    }

    override fun getUser(): String? {
        return bankLocalDataSource.getEncryptedUser()?.let {
            rsaCrypto.decrypt(it)
        }
    }
}
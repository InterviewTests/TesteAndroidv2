package com.jeanjnap.domain.usecase

import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.repository.BankRepository
import com.jeanjnap.infrastructure.crypto.RSACrypto

class BankUseCaseImpl(
    private val bankRepository: BankRepository,
    private val rsaCrypto: RSACrypto
): BankUseCase {
    override suspend fun login(username: String, password: String): Response<UserAccount> {
        return bankRepository.login(username, password).also {
            if (it is SuccessResponse) {
                bankRepository.saveEncryptedUser(rsaCrypto.encrypt(username))
            }
        }
    }

    override suspend fun getStatements(userId: Long?): Response<List<Statement>> {
        return bankRepository.getStatements(userId)
    }

    override fun getUser(): String? {
        return bankRepository.getUser()?.let {
            rsaCrypto.decrypt(it)
        }
    }
}

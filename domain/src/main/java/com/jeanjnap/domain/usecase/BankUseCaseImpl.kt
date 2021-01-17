package com.jeanjnap.domain.usecase

import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.repository.BankRepository

class BankUseCaseImpl(
    private val bankRepository: BankRepository
): BankUseCase {
    override suspend fun login(username: String, password: String): Response<UserAccount> {
        return bankRepository.login(username, password)
    }

    override suspend fun getStatements(userId: Long?): Response<List<Statement>> {
        return bankRepository.getStatements(userId)
    }

    override fun getUser(): String? {
        return bankRepository.getUser()
    }
}

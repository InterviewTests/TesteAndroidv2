package com.jeanjnap.domain.repository

import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.UserAccount

interface BankRepository {
    suspend fun login(username: String, password: String): Response<UserAccount>
    suspend fun getStatements(userId: Long?): Response<List<Statement>>
    fun saveEncryptedUser(user: String)
    fun getUser(): String?
}

package com.jeanjnap.domain.repository

import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.UserAccount

interface BankRepository {
    suspend fun login(username: String, password: String): Response<UserAccount>
    fun getUser(): String?
}

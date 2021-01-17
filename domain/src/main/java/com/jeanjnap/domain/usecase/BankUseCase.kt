package com.jeanjnap.domain.usecase

import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.UserAccount

interface BankUseCase {
    suspend fun login(username: String, password: String): Response<UserAccount>
    fun getUser(): String?
}
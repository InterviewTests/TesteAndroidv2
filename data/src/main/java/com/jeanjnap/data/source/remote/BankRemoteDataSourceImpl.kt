package com.jeanjnap.data.source.remote

import com.jeanjnap.data.api.create
import com.jeanjnap.data.mapper.UserDataResponseToUserAccountMapper
import com.jeanjnap.data.source.remote.service.BankService
import com.jeanjnap.domain.entity.ErrorResponse
import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.UserAccount
import com.jeanjnap.domain.entity.error.RequestError
import java.lang.Exception

class BankRemoteDataSourceImpl(
    private val bankService: BankService,
    private val userDataResponseToUserAccountMapper: UserDataResponseToUserAccountMapper
) : BankRemoteDataSource {
    override suspend fun login(username: String, password: String): Response<UserAccount> {
        return try {
            bankService.loginAsync(username, password).await()
                .create(userDataResponseToUserAccountMapper)
        } catch (e: Exception) {
            ErrorResponse(RequestError(errorMessage = e.message))
        }
    }
}

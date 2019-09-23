package com.gustavo.bankandroid.login.usecase

import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.mock.MockData
import io.reactivex.Single

class AuthenticateUserUseCaseMock : LoginUseCases.AuthenticateUserUseCase {
    private val userInfoMock
        get() = MockData.mockUserInfo()

    override fun execute(username: String, password: String): Single<LoginResponse> {
        return Single.just(LoginResponse.Success(userInfoMock))
    }
}
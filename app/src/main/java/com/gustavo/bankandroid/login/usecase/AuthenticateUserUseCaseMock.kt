package com.gustavo.bankandroid.login.usecase

import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import io.reactivex.Single

class AuthenticateUserUseCaseMock : LoginUseCases.AuthenticateUserUseCase {
    private val userInfoMock
        get() = UserInfo(1, "username", "1234", "name", "account", 1000)

    override fun execute(username: String, password: String): Single<LoginResponse> {
        return Single.just(LoginResponse.Success(userInfoMock))
    }
}
package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.entity.UserLoginResponse
import com.gustavo.bankandroid.mock.MockData
import io.reactivex.Single

class AuthenticateUserUseCaseMock : LoginUseCases.AuthenticateUserUseCase {
    private val userInfoMock
        get() = MockData.mockUserInfo()

    override fun execute(username: String, password: String): Single<UserLoginResponse> {
        return Single.just(UserLoginResponse.Success(userInfoMock))
    }
}
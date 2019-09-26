package com.gustavo.bankandroid.common.mock.usecase

import com.gustavo.bankandroid.common.mock.MockData
import com.gustavo.bankandroid.domain.contracts.LoginUseCases
import com.gustavo.bankandroid.entity.UserLoginResponse
import io.reactivex.Single

//mock used for ui testing purpose
class AuthenticateUserUseCaseMock :
    LoginUseCases.AuthenticateUserUseCase {
    private val userInfoMock
        get() = MockData.mockUserInfo()

    override fun execute(username: String, password: String): Single<UserLoginResponse> {
        return Single.just(UserLoginResponse.Success(userInfoMock))
    }
}
package com.gustavo.bankandroid.domain.contracts

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import io.reactivex.Single

interface LoginUseCases {
    interface AuthenticateUserUseCase {
        fun execute(username: String, password: String): Single<UserLoginResponse>
    }
    interface StoreUserInfoUseCase {
        fun execute(userInfo: UserInfo)
    }
    interface ValidatePassword{
        operator fun invoke(password: String): Boolean
    }
    interface ValidateUserName{
        operator fun invoke(username: String): Boolean
    }
}
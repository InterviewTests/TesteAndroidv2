package com.gustavo.bankandroid.features.login.usecase

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
}
package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import io.reactivex.Single

interface LoginUseCases {
    interface AuthenticateUserUseCase {
        fun execute(username: String, password: String): Single<LoginResponse>
    }
    interface StoreUserInfoUseCase {
        fun execute(userInfo: UserInfo)
    }
}
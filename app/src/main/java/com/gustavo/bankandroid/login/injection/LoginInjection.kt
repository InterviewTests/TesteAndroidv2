package com.gustavo.bankandroid.login.injection

import com.gustavo.bankandroid.login.usecase.LoginUseCases

interface LoginInjection {
    val authenticateUserUseCase:LoginUseCases.AuthenticateUserUseCase
    val storeUserInfoUseCase:LoginUseCases.StoreUserInfoUseCase
}
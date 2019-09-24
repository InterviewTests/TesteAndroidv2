package com.gustavo.bankandroid.features.login.injection

import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.gustavo.bankandroid.features.login.usecase.LoginUseCases

interface LoginModule {
    val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase
    val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase
    val userRepository: UserRepository
    val database: UserDatabase
}
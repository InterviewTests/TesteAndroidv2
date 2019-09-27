package com.gustavo.bankandroid.features.login.injection

import com.gustavo.bankandroid.datasource.database.UserDatabase
import com.gustavo.bankandroid.domain.contracts.LoginUseCases
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract

interface LoginModule {
    val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase
    val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase
    val validateUserName: LoginUseCases.ValidateUserName
    val validatePassword: LoginUseCases.ValidatePassword
    val userRepository: RepositoriesContract.UserRepository
    val database: UserDatabase
}
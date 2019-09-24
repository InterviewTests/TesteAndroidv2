package com.gustavo.bankandroid.features.login.injection

import android.content.Context
import androidx.room.Room
import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.contants.Constants
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.gustavo.bankandroid.features.login.repository.UserRepositoryImpl
import com.gustavo.bankandroid.features.login.usecase.AuthenticateUserUseCaseImpl
import com.gustavo.bankandroid.features.login.usecase.LoginUseCases
import com.gustavo.bankandroid.features.login.usecase.StoreUserInfoUseCaseImpl

class LoginModuleImpl(private val context: Context) : LoginModule {
    override val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase by lazy {
        AuthenticateUserUseCaseImpl(userRepository)
    }
    override val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase by lazy {
        StoreUserInfoUseCaseImpl(userRepository)
    }
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(database, ServerIterator())
    }

    override val database: UserDatabase by lazy {
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            Constants.USER_DATABASE
        ).build()
    }
}
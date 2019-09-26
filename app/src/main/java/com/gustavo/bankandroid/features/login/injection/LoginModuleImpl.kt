package com.gustavo.bankandroid.features.login.injection

import android.content.Context
import androidx.room.Room
import com.gustavo.bankandroid.common.contants.Constants
import com.gustavo.bankandroid.datasource.api.ServerIterator
import com.gustavo.bankandroid.datasource.database.UserDatabase
import com.gustavo.bankandroid.datasource.repository.UserRepositoryImpl
import com.gustavo.bankandroid.domain.contracts.LoginUseCases
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.features.login.usecase.AuthenticateUserUseCaseImpl
import com.gustavo.bankandroid.features.login.usecase.StoreUserInfoUseCaseImpl
import com.gustavo.bankandroid.features.login.usecase.ValidatePasswordImpl
import com.gustavo.bankandroid.features.login.usecase.ValidateUsernameImpl

class LoginModuleImpl(private val context: Context) : LoginModule {
    override val validateUserName: LoginUseCases.ValidateUserName by lazy {
        ValidateUsernameImpl()
    }
    override val validatePassword: LoginUseCases.ValidatePassword by lazy {
        ValidatePasswordImpl()
    }
    override val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase by lazy {
        AuthenticateUserUseCaseImpl(userRepository)
    }
    override val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase by lazy {
        StoreUserInfoUseCaseImpl(userRepository)
    }
    override val userRepository: RepositoriesContract.UserRepository by lazy {
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
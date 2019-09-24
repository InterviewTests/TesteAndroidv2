package com.gustavo.bankandroid.features.statements.injection

import android.content.Context
import androidx.room.Room
import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.contants.Constants
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.gustavo.bankandroid.features.login.repository.UserRepositoryImpl
import com.gustavo.bankandroid.features.statements.repository.DataRepository
import com.gustavo.bankandroid.features.statements.repository.DataRepositoryImpl
import com.gustavo.bankandroid.features.statements.usecase.ClearUserInfoUseCaseImpl
import com.gustavo.bankandroid.features.statements.usecase.FetchStatementUseCaseImpl
import com.gustavo.bankandroid.features.statements.usecase.GetLoggedUserInfoUseCaseImpl
import com.gustavo.bankandroid.features.statements.usecase.StatementUseCases

class StatementModuleImpl(private val context: Context) : StatementModule {
    override val serverIterator by lazy {
        ServerIterator()
    }

    override val fetchStatementUseCase: StatementUseCases.FetchStatementUseCase by lazy {
        FetchStatementUseCaseImpl(dataRepository)
    }
    override val getLoggedUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase by lazy {
        GetLoggedUserInfoUseCaseImpl(userRepository)
    }
    override val clearUserInfoUseCase: StatementUseCases.ClearUserInfoUseCase by lazy {
        ClearUserInfoUseCaseImpl(userRepository)
    }

    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(database, serverIterator)
    }
    override val database: UserDatabase by lazy {
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            Constants.USER_DATABASE
        ).build()
    }
    override val dataRepository: DataRepository by lazy {
        DataRepositoryImpl(serverIterator)
    }

}
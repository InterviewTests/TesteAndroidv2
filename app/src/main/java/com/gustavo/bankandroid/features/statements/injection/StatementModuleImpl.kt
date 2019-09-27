package com.gustavo.bankandroid.features.statements.injection

import android.content.Context
import androidx.room.Room
import com.gustavo.bankandroid.common.contants.Constants
import com.gustavo.bankandroid.datasource.api.ServerIterator
import com.gustavo.bankandroid.datasource.database.UserDatabase
import com.gustavo.bankandroid.datasource.repository.DataRepositoryImpl
import com.gustavo.bankandroid.datasource.repository.UserRepositoryImpl
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.domain.contracts.StatementUseCases
import com.gustavo.bankandroid.features.statements.usecase.ClearUserInfoUseCaseImpl
import com.gustavo.bankandroid.features.statements.usecase.FetchStatementUseCaseImpl
import com.gustavo.bankandroid.features.statements.usecase.GetLoggedUserInfoUseCaseImpl

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

    override val userRepository: RepositoriesContract.UserRepository by lazy {
        UserRepositoryImpl(database, serverIterator)
    }
    override val database: UserDatabase by lazy {
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            Constants.USER_DATABASE
        ).build()
    }
    override val dataRepository: RepositoriesContract.DataRepository by lazy {
        DataRepositoryImpl(serverIterator)
    }

}
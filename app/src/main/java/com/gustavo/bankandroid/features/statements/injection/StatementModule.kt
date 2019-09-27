package com.gustavo.bankandroid.features.statements.injection

import com.gustavo.bankandroid.datasource.api.ServerIterator
import com.gustavo.bankandroid.datasource.database.UserDatabase
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.domain.contracts.StatementUseCases

interface StatementModule {
    val fetchStatementUseCase: StatementUseCases.FetchStatementUseCase
    val getLoggedUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase
    val clearUserInfoUseCase: StatementUseCases.ClearUserInfoUseCase
    val dataRepository: RepositoriesContract.DataRepository
    val userRepository: RepositoriesContract.UserRepository
    val database: UserDatabase
    val serverIterator: ServerIterator
}
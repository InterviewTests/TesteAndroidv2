package com.gustavo.bankandroid.features.statements.injection

import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.gustavo.bankandroid.features.statements.repository.DataRepository
import com.gustavo.bankandroid.features.statements.usecase.StatementUseCases

interface StatementInjection {
    val fetchStatementUseCase: StatementUseCases.FetchStatementUseCase
    val getLoggedUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase
    val dataRepository: DataRepository
    val userRepository: UserRepository
    val database: UserDatabase
    val serverIterator: ServerIterator
}
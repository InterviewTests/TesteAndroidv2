package com.gustavo.bankandroid.statements.injection

import com.gustavo.bankandroid.statements.repository.DataRepository
import com.gustavo.bankandroid.statements.usecase.StatementUseCases

interface StatementInjection {
    val fetchStatementUseCase: StatementUseCases.FetchStatementUseCase
    val getLoggedUserInfoUseCase: StatementUseCases.GetLoggedUserInfoUseCase
    val dataRepository: DataRepository
}
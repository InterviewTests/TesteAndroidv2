package com.santander.domain.di

import com.santander.domain.usecase.*
import com.santander.domain.usecase.impl.*
import org.koin.dsl.module.module

object UseCaseModule {

    val module = module {

        single<IGetUserUseCase> {
            GetUserUseCaseImpl(loginRepository = get())
        }

        single<ILoginUseCase> {
            LoginUseCaseImpl(loginRepository = get(), accountRepository = get())
        }

        single<IGetAccountUseCase> {
            GetAccountUseCase(accountRepository = get())
        }

        single<ICleanAccountUseCase> {
            CleanAccountUseCase(accountRepository = get())
        }

        single<IFetchStatementUseCase> {
            FetchStatementUseCaseImpl(statementRepository = get())
        }
    }
}
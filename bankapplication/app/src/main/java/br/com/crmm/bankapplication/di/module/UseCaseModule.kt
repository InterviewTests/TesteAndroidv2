package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.domain.repository.LoginRepository
import br.com.crmm.bankapplication.domain.repository.StatementRepository
import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import br.com.crmm.bankapplication.domain.usecase.StatementUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideLoginUseCase(get()) }
    single { provideStatementUseCase(get()) }
}

fun provideLoginUseCase(loginRepository: LoginRepository) = LoginUseCase(loginRepository)

fun provideStatementUseCase(statementRepository: StatementRepository) = StatementUseCase(statementRepository)
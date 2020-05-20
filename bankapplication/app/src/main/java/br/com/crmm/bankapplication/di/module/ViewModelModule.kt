package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import br.com.crmm.bankapplication.domain.usecase.StatementUseCase
import br.com.crmm.bankapplication.framework.presentation.ui.login.LoginViewModel
import br.com.crmm.bankapplication.framework.presentation.ui.statement.StatementViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { provideLoginViewModel(get()) }
    factory { provideStatementViewModel(get()) }
}

fun provideLoginViewModel(loginUseCase: LoginUseCase): LoginViewModel {
    return LoginViewModel(loginUseCase)
}

fun provideStatementViewModel(statementUseCase: StatementUseCase): StatementViewModel {
    return StatementViewModel(statementUseCase)
}

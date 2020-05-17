package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import br.com.crmm.bankapplication.framework.presentation.ui.login.LoginViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { provideLoginViewModel(get()) }
}

fun provideLoginViewModel(loginUseCase: LoginUseCase): LoginViewModel {
    return LoginViewModel(loginUseCase)
}

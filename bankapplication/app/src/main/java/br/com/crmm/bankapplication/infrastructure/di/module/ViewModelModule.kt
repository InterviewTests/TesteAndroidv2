package br.com.crmm.bankapplication.infrastructure.di.module

import br.com.crmm.bankapplication.presentation.ui.login.LoginViewModel
import org.koin.dsl.module

val loginViewModelModule = module {
    factory { LoginViewModel(get()) }
}

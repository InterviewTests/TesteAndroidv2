package br.com.crmm.bankapplication.infrastructure.di.module

import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import org.koin.dsl.module

val loginUseCaseModule = module {
    single { LoginUseCase() }
}
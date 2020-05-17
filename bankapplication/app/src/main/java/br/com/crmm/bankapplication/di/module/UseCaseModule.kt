package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.domain.repository.LoginRepository
import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideLoginUseCase(get()) }
}

fun provideLoginUseCase(loginRepository: LoginRepository) = LoginUseCase(loginRepository)
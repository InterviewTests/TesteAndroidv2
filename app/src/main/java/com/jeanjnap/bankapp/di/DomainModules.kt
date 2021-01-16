package com.jeanjnap.bankapp.di

import com.jeanjnap.domain.usecase.BankUseCase
import com.jeanjnap.domain.usecase.BankUseCaseImpl
import org.koin.dsl.module

object DomainModules {

    val domainModulesItems = module {
        //USECASES
        single<BankUseCase> { BankUseCaseImpl(get()) }
    }
}

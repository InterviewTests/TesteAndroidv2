package com.example.bankapp.di.modules

import com.example.domain.usecases.ListStatementsUseCase
import com.example.domain.usecases.PerformLoginUseCase
import org.koin.dsl.module

internal val DomainModules = module {
    factory { ListStatementsUseCase(repository = get()) }
    factory { PerformLoginUseCase(repository = get()) }
}
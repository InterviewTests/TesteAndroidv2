package com.zuptest.santander.domain.di

import com.zuptest.santander.domain.business.usecase.DoLoginUseCase
import com.zuptest.santander.domain.business.usecase.ListStatementsUseCase
import com.zuptest.santander.domain.business.usecase.impl.DoLoginUseCaseImpl
import com.zuptest.santander.domain.business.usecase.impl.GetStatementsUseCaseImpl
import org.koin.dsl.module

object DomainModule {

    val module = module {

        factory<DoLoginUseCase> {
            DoLoginUseCaseImpl(repository = get())
        }

        factory<ListStatementsUseCase> {
            GetStatementsUseCaseImpl(repository = get())
        }
    }
}
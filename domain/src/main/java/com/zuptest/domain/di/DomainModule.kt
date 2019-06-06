package com.zuptest.domain.di

import com.zuptest.domain.business.usecase.DoLoginUseCase
import com.zuptest.domain.business.usecase.GetStatementsUseCase
import com.zuptest.domain.business.usecase.impl.DoLoginUseCaseImpl
import com.zuptest.domain.business.usecase.impl.GetStatementsUseCaseImpl
import org.koin.dsl.module

object DomainModule {

    val module = module {

        factory<DoLoginUseCase> {
            DoLoginUseCaseImpl(repository = get())
        }

        factory<GetStatementsUseCase> {
            GetStatementsUseCaseImpl(repository = get())
        }
    }
}
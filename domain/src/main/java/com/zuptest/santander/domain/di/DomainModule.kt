package com.zuptest.santander.domain.di

import com.zuptest.santander.domain.business.usecase.DoLoginUseCase
import com.zuptest.santander.domain.business.usecase.ListStatementsUseCase
import com.zuptest.santander.domain.business.usecase.RetrieveLastLoginUseCase
import com.zuptest.santander.domain.business.usecase.SaveSuccessfulLoginInfoUseCase
import com.zuptest.santander.domain.business.usecase.impl.DoLoginUseCaseImpl
import com.zuptest.santander.domain.business.usecase.impl.ListStatementsUseCaseImpl
import com.zuptest.santander.domain.business.usecase.impl.RetrieveLastLoginUseCaseImpl
import com.zuptest.santander.domain.business.usecase.impl.SaveSuccessfulLoginInfoUseCaseImpl
import org.koin.dsl.module

object DomainModule {

    val module = module {

        factory<DoLoginUseCase> {
            DoLoginUseCaseImpl(repository = get())
        }

        factory<ListStatementsUseCase> {
            ListStatementsUseCaseImpl(repository = get())
        }

        factory<SaveSuccessfulLoginInfoUseCase> {
            SaveSuccessfulLoginInfoUseCaseImpl(repository = get())
        }

        factory<RetrieveLastLoginUseCase> {
            RetrieveLastLoginUseCaseImpl(repository = get())
        }
    }
}

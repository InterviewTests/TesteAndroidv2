package com.santander.app.core.di

import com.nhaarman.mockitokotlin2.mock
import com.santander.domain.di.UseCaseModule
import com.santander.domain.repository.IAccountRepository
import com.santander.domain.repository.ILoginRepository
import com.santander.domain.repository.IStatementRepository
import org.koin.dsl.module.module

val RepositoryModuleTest = module {
    single { mock<ILoginRepository>() }
    single { mock<IAccountRepository>() }
    single { mock<IStatementRepository>() }
}

val PresenterModuleTest = PresenterModule.module

val UserCaseModuleTest = UseCaseModule.module

val testModules = listOf(RepositoryModuleTest, UserCaseModuleTest, PresenterModuleTest)
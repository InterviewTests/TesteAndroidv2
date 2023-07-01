package com.nandoligeiro.safrando.di

import com.nandoligeiro.safrando.data.bankstatement.mapper.BankStatementDataToDomainMapper
import com.nandoligeiro.safrando.data.login.mapper.LoginDataToDomainMapper
import com.nandoligeiro.safrando.datasource.bankstatement.mapper.BankStatementEntityToDataMapper
import com.nandoligeiro.safrando.datasource.login.mapper.LoginEntityToDataMapper
import com.nandoligeiro.safrando.presentation.login.mapper.LoginDomainToUiMapper
import com.nandoligeiro.safrando.presentation.statements.mapper.BankStatementDomainToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun providesLoginDomainToUiMapper() = LoginDomainToUiMapper()

    @Provides
    fun providesBankStatementDomainToUiMapper() = BankStatementDomainToUiMapper()

    @Provides
    fun providesLoginDataToDomainMapper() = LoginDataToDomainMapper()

    @Provides
    fun providesLoginEntityToDataMapper() = LoginEntityToDataMapper()

    @Provides
    fun providesBankStatementEntityToDataMapper() = BankStatementEntityToDataMapper()

    @Provides
    fun providesBankStatementDataToDomainMapper() = BankStatementDataToDomainMapper()
}


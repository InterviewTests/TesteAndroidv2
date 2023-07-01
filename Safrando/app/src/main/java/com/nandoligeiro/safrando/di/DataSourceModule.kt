package com.nandoligeiro.safrando.di


import com.nandoligeiro.safrando.data.bankstatement.datasource.BankStatementDataSource
import com.nandoligeiro.safrando.data.login.datasource.LoginDataSource
import com.nandoligeiro.safrando.datasource.bankstatement.BankStatementDataSourceImpl
import com.nandoligeiro.safrando.datasource.login.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLoginDataSource(repository: LoginDataSourceImpl): LoginDataSource

    @Binds
    fun bindBankStatementDataSource(repository: BankStatementDataSourceImpl): BankStatementDataSource


}
package com.nandoligeiro.safrando.di


import com.nandoligeiro.safrando.data.bankstatement.datasource.BankStatementDataSource
import com.nandoligeiro.safrando.data.login.datasource.LocalLoginDataSource
import com.nandoligeiro.safrando.data.login.datasource.RemoteLoginDataSource
import com.nandoligeiro.safrando.datasource.bankstatement.remote.BankStatementDataSourceImpl
import com.nandoligeiro.safrando.datasource.login.local.LocalLoginDataSourceImpl
import com.nandoligeiro.safrando.datasource.login.remote.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLoginDataSource(repository: LoginDataSourceImpl): RemoteLoginDataSource

    @Binds
    fun bindLocalLoginDataSource(repository: LocalLoginDataSourceImpl): LocalLoginDataSource


    @Binds
    fun bindBankStatementDataSource(repository: BankStatementDataSourceImpl): BankStatementDataSource

}

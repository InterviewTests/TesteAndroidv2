package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.data.source.remote.abstraction.LoginRemoteDataSource
import br.com.crmm.bankapplication.data.source.remote.abstraction.StatementRemoteDataSource
import br.com.crmm.bankapplication.data.source.remote.implementation.LoginDataSourceImpl
import br.com.crmm.bankapplication.data.source.remote.implementation.StatementDataSourceImpl
import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.LoginService
import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.StatementService
import org.koin.dsl.module

val remoteDataSource = module {
    single { provideLoginRemoteDataSource(get()) }
    single { provideStatementRemoteDataSource(get()) }
}

fun provideLoginRemoteDataSource(loginService: LoginService): LoginRemoteDataSource{
    return LoginDataSourceImpl(loginService)
}

fun provideStatementRemoteDataSource(statementService: StatementService): StatementRemoteDataSource{
    return StatementDataSourceImpl(statementService)
}
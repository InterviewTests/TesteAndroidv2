package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.data.source.remote.abstraction.LoginRemoteDataSource
import br.com.crmm.bankapplication.data.source.remote.implementation.LoginDataSourceImpl
import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.LoginService
import org.koin.dsl.module

val remoteDataSource = module {
    single { provideLoginRemoteDataSource(get()) }
}

fun provideLoginRemoteDataSource(loginService: LoginService): LoginRemoteDataSource{
    return LoginDataSourceImpl(loginService)
}
package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.data.repository.LoginRepositoryImpl
import br.com.crmm.bankapplication.data.source.remote.abstraction.LoginRemoteDataSource
import br.com.crmm.bankapplication.domain.repository.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideRepositoryModule(get()) }
}

fun provideRepositoryModule(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository {
    return LoginRepositoryImpl(loginRemoteDataSource)
}
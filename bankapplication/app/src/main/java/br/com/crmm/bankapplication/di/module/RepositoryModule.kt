package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.data.repository.LoginRepositoryImpl
import br.com.crmm.bankapplication.data.repository.StatementRepositoryImpl
import br.com.crmm.bankapplication.data.source.remote.abstraction.LoginRemoteDataSource
import br.com.crmm.bankapplication.data.source.remote.abstraction.StatementRemoteDataSource
import br.com.crmm.bankapplication.domain.repository.LoginRepository
import br.com.crmm.bankapplication.domain.repository.StatementRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideLoginRepositoryModule(get()) }
    single { provideStatementRepositoryModule(get()) }
}

fun provideLoginRepositoryModule(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository {
    return LoginRepositoryImpl(loginRemoteDataSource)
}

fun provideStatementRepositoryModule(statementRemoteDataSource: StatementRemoteDataSource): StatementRepository {
    return StatementRepositoryImpl(statementRemoteDataSource)
}
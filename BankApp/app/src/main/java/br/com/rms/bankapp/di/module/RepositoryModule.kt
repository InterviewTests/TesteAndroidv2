package br.com.rms.bankapp.di.module

import br.com.rms.bankapp.data.repository.statement.StatementRepository
import br.com.rms.bankapp.data.repository.statement.StatementRepositoryContract
import br.com.rms.bankapp.data.repository.user.UserRepository
import br.com.rms.bankapp.data.repository.user.UserRepositoryContract
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideUserRepository(userRepository: UserRepository):UserRepositoryContract

    @Singleton
    @Binds
    abstract fun provideStatementRepository(statementRepository: StatementRepository): StatementRepositoryContract


}
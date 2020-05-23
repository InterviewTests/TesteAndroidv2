package br.com.bankapp.di.main

import br.com.bankapp.data.api.BankAppApiService
import br.com.bankapp.data.db.BankDatabase
import br.com.bankapp.data.db.dao.StatementDao
import br.com.bankapp.data.repository.StatementRepositoryImpl
import br.com.bankapp.data.source.StatementDataSource
import br.com.bankapp.domain.repository.StatementRepository
import dagger.Module
import dagger.Provides


@Module
object MainModule {

    @JvmStatic
    @MainScope
    @Provides
    fun provideStatementDao(db: BankDatabase): StatementDao {
        return db.statementDao()
    }

    @JvmStatic
    @MainScope
    @Provides
    fun provideStatementDataSource(
        apiService: BankAppApiService,
        statementDao: StatementDao
    ): StatementDataSource {
        return StatementDataSource(apiService, statementDao)
    }

    @JvmStatic
    @MainScope
    @Provides
    fun provideStatementRepository(
        statementDataSource: StatementDataSource
    ): StatementRepository {
        return StatementRepositoryImpl(statementDataSource)
    }
}
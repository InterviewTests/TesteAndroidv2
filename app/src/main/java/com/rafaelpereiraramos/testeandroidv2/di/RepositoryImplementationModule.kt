package com.rafaelpereiraramos.testeandroidv2.di

import com.rafaelpereiraramos.testeandroidv2.api.BankApiService
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors
import com.rafaelpereiraramos.testeandroidv2.db.dao.ParameterDao
import com.rafaelpereiraramos.testeandroidv2.db.dao.StatementDao
import com.rafaelpereiraramos.testeandroidv2.db.dao.UserDao
import com.rafaelpereiraramos.testeandroidv2.repo.ParameterRepo
import com.rafaelpereiraramos.testeandroidv2.repo.StatementRepo
import com.rafaelpereiraramos.testeandroidv2.repo.UserRepo
import com.rafaelpereiraramos.testeandroidv2.repo.impl.ParameterRepoImpl
import com.rafaelpereiraramos.testeandroidv2.repo.impl.StatementRepoImpl
import com.rafaelpereiraramos.testeandroidv2.repo.impl.UserRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 06/12/2018.
 */
@Module
class RepositoryImplementationModule {

    @Provides
    @Singleton
    fun provideUserRepositoryImplementation(userDao: UserDao, apiService: BankApiService, appExecutors: AppExecutors): UserRepo
            = UserRepoImpl(userDao, apiService, appExecutors)

    @Provides
    @Singleton
    fun provideStatementRepositoryImplementation(statementDao: StatementDao, apiService: BankApiService, appExecutors: AppExecutors): StatementRepo
            = StatementRepoImpl(appExecutors, statementDao, apiService)

    @Provides
    @Singleton
    fun provideParameterRepositoryImplementation(parameterDao: ParameterDao, appExecutors: AppExecutors): ParameterRepo
            = ParameterRepoImpl(parameterDao, appExecutors)
}
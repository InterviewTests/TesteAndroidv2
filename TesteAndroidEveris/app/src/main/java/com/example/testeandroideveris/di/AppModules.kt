package com.example.testeandroideveris.di

import com.example.testeandroideveris.feature.login.data.datasource.LoginLocalDataSource
import com.example.testeandroideveris.feature.login.data.datasource.LoginLocalDataSourceImpl
import com.example.testeandroideveris.feature.login.data.datasource.LoginRemoteDataSource
import com.example.testeandroideveris.feature.login.data.datasource.LoginRemoteDataSourceImpl
import com.example.testeandroideveris.feature.login.data.repository.LoginRepositoryImpl
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import com.example.testeandroideveris.feature.login.domain.usecases.LoginUseCase
import com.example.testeandroideveris.feature.login.presentation.LoginViewModel
import com.example.testeandroideveris.feature.statements.data.datasource.StatementRemoteDataSource
import com.example.testeandroideveris.feature.statements.data.datasource.StatementRemoteDataSourceImpl
import com.example.testeandroideveris.feature.statements.data.repository.StatementRepositoryImpl
import com.example.testeandroideveris.feature.statements.domain.repository.StatementRepository
import com.example.testeandroideveris.feature.statements.domain.usecases.StatementUseCase
import com.example.testeandroideveris.feature.statements.presentation.StatementsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val URL_API = "https://bank-app-test.herokuapp.com/api"

val appModules = module {

    single<LoginLocalDataSource> {
        LoginLocalDataSourceImpl(androidContext())
    }

    single<LoginRemoteDataSource> {
        LoginRemoteDataSourceImpl()
    }

    single<StatementRemoteDataSource> {
        StatementRemoteDataSourceImpl()
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get(), get())
    }

    single<StatementRepository> {
        StatementRepositoryImpl(get())
    }

    factory { LoginUseCase(get()) }
    factory { StatementUseCase(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { StatementsViewModel(get()) }
}
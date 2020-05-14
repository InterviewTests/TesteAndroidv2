package com.example.testeandroideveris.di

import com.example.testeandroideveris.feature.login.data.datasource.LocalDataSource
import com.example.testeandroideveris.feature.login.data.datasource.LocalDataSourceImpl
import com.example.testeandroideveris.feature.login.data.repository.LoginRepositoryImpl
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import com.example.testeandroideveris.feature.login.domain.usecases.LoginUseCase
import com.example.testeandroideveris.feature.login.presentation.LoginViewModel
import com.example.testeandroideveris.feature.statements.data.repository.StatementRepositoryImpl
import com.example.testeandroideveris.feature.statements.domain.repository.StatementRepository
import com.example.testeandroideveris.feature.statements.domain.usecases.StatementUseCase
import com.example.testeandroideveris.feature.statements.presentation.StatementsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val URL_API = "https://bank-app-test.herokuapp.com/api"

val appModules = module {

    single<LocalDataSource> {
        LocalDataSourceImpl(androidContext())
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    single<StatementRepository> {
        StatementRepositoryImpl()
    }

    factory { LoginUseCase(get()) }
    factory { StatementUseCase(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { StatementsViewModel(get()) }
    
}
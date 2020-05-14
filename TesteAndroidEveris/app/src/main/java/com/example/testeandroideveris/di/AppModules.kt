package com.example.testeandroideveris.di

import com.example.testeandroideveris.feature.login.data.repository.LoginRepositoryImpl
import com.example.testeandroideveris.feature.login.domain.repository.LoginRepository
import com.example.testeandroideveris.feature.login.domain.usecases.LoginUseCase
import com.example.testeandroideveris.feature.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val URL_API = "https://bank-app-test.herokuapp.com/api"

val appModules = module {

    single<LoginRepository> {
        LoginRepositoryImpl()
    }

    factory { LoginUseCase(get()) }
    viewModel { LoginViewModel(get()) }
    
}
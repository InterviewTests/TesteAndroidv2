package com.br.myapplication.di

import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.home.HomeViewModel
import com.br.myapplication.login.LoginViewModel
import com.br.myapplication.repository.HomeRepository
import com.br.myapplication.repository.LoginRepository
import com.br.myapplication.service.RetrofitConfiguration
import com.br.myapplication.usercase.HomeUserCase
import com.br.myapplication.usercase.LoginUserCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel

import org.koin.dsl.module.module

val listModules = listOf(
    module {
        single {RetrofitConfiguration().getAppRequest()}
        single { SharedPreferencesManager(androidApplication()) }

        factory { LoginRepository(get()) }
        single { LoginUserCase(get()) }
        viewModel { LoginViewModel(get(), get()) }

        factory { HomeRepository(get()) }
        single { HomeUserCase(get()) }
        viewModel { HomeViewModel(get(), get()) }
    }
)

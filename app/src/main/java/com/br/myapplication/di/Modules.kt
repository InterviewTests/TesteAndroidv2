package com.br.myapplication.di

import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.home.HomeViewModel
import com.br.myapplication.login.LoginViewModel
import com.br.myapplication.repository.HomeRepository
import com.br.myapplication.repository.LoginRepository
import com.br.myapplication.repository.UserRepository
import com.br.myapplication.service.RetrofitConfiguration
import com.br.myapplication.usecase.HomeUseCase
import com.br.myapplication.usecase.LoginUseCase
import com.br.myapplication.usecase.UserUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel

import org.koin.dsl.module.module

val listModules = listOf(
    module {
        single {RetrofitConfiguration().getAppRequest()}
        single { SharedPreferencesManager(androidApplication()) }

        factory { UserRepository(get()) }
        single { UserUseCase(get()) }

        factory { LoginRepository(get()) }
        single { LoginUseCase(get()) }
        viewModel { LoginViewModel(get(), get()) }

        factory { HomeRepository(get()) }
        single { HomeUseCase(get()) }
        viewModel { HomeViewModel(get(), get()) }
    }
)

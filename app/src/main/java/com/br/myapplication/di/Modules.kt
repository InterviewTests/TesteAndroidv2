package com.br.myapplication.di

import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.presentation.home.HomeViewModel
import com.br.myapplication.presentation.login.LoginViewModel
import com.br.myapplication.data.repository.HomeRepository
import com.br.myapplication.data.repository.LoginRepository
import com.br.myapplication.data.repository.UserRepository
import com.br.myapplication.data.service.RetrofitConfiguration
import com.br.myapplication.domain.usecase.HomeUseCase
import com.br.myapplication.domain.usecase.LoginUseCase
import com.br.myapplication.domain.usecase.UserUseCase
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

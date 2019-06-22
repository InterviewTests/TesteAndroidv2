package com.farage.testeandroidv2.di

import com.farage.testeandroidv2.datasource.StatementDataSource
import com.farage.testeandroidv2.datasource.UserDataSource
import com.farage.testeandroidv2.datasource.retrofit.RetrofitConfiguration
import com.farage.testeandroidv2.domain.StatementRepository
import com.farage.testeandroidv2.domain.UserRepository
import com.farage.testeandroidv2.domain.usecase.StatementsUseCase
import com.farage.testeandroidv2.domain.usecase.UserLoginUseCase
import com.farage.testeandroidv2.repository.StatementRepositoryImpl
import com.farage.testeandroidv2.repository.UserRepositoryImpl
import com.farage.testeandroidv2.router.LoginRouter
import com.farage.testeandroidv2.ui.LoginActivity
import com.farage.testeandroidv2.viewmodel.HomeViewModel
import com.farage.testeandroidv2.viewmodel.UserViewModel
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.coroutines.ExperimentalCoroutinesApi

class KodeinContainers {

    companion object {
        @ExperimentalCoroutinesApi
        val diBaseProject = Kodein {
            bind<RetrofitConfiguration>() with provider { RetrofitConfiguration() }
            bind<UserDataSource>() with provider { UserDataSource(instance()) }
            bind<UserRepository>() with provider { UserRepositoryImpl(instance()) }
            bind<UserLoginUseCase>() with provider { UserLoginUseCase(instance()) }
            bind<LoginRouter>() with provider { LoginRouter() }
            bind<LoginActivity>() with provider { LoginActivity() }
            bind<UserViewModel>() with provider { UserViewModel(instance(), instance()) }

            bind<StatementsUseCase>() with provider { StatementsUseCase(instance()) }
            bind<StatementRepository>() with provider { StatementRepositoryImpl(instance()) }
            bind<StatementDataSource>() with provider { StatementDataSource(instance()) }
            bind<HomeViewModel>() with provider { HomeViewModel(instance()) }
        }
    }

}
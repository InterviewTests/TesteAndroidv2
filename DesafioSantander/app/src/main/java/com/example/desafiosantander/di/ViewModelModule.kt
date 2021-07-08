package com.example.desafiosantander.di

import com.example.desafiosantander.feature.login.LoginViewModel
import com.example.desafiosantander.feature.summary.SummaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get(), get()) }
    viewModel { SummaryViewModel(get(), get()) }

}
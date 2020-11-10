package com.example.bankapp.di.modules

import com.example.bankapp.ui.login.LoginViewModel
import com.example.bankapp.ui.statements.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val UiModules = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get()) }
}
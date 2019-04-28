package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.feature.login.LoginViewModel
import br.com.alex.bankappchallenge.feature.statement.StatementViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get(), get()) }
    viewModel { StatementViewModel(get(), get()) }
}
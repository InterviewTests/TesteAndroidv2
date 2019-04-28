package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.feature.login.LoginReducer
import br.com.alex.bankappchallenge.feature.login.LoginReducerContract
import org.koin.dsl.module

val reducerModule = module {
    factory<LoginReducerContract> { LoginReducer() }
}
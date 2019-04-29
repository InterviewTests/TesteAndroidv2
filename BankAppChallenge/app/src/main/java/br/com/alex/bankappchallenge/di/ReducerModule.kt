package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.feature.login.LoginReducer
import br.com.alex.bankappchallenge.feature.login.LoginReducerContract
import br.com.alex.bankappchallenge.feature.statement.StatementReducer
import br.com.alex.bankappchallenge.feature.statement.StatementReducerContract
import br.com.alex.bankappchallenge.feature.statement.UserAccountReducer
import br.com.alex.bankappchallenge.feature.statement.UserAccountReducerContract
import org.koin.dsl.module

val reducerModule = module {
    factory<LoginReducerContract> { LoginReducer() }
    factory<StatementReducerContract> { StatementReducer() }
    factory<UserAccountReducerContract> { UserAccountReducer() }
}
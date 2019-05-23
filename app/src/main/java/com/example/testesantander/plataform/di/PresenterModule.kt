package com.example.testesantander.plataform.di

import com.example.testesantander.ui.login.LoginContract
import com.example.testesantander.ui.login.LoginPresenter
import com.example.testesantander.ui.statements.StatementsContract
import com.example.testesantander.ui.statements.StatementsPresenter
import org.koin.dsl.module.module

val presenterModule = module {
    factory { LoginPresenter(get()) as LoginContract.Presenter }
    factory { StatementsPresenter(get()) as StatementsContract.Presenter }
}
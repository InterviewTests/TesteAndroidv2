package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.flow.dashboard.DashboardHandler
import br.com.mdr.testeandroid.flow.signin.SignInHandler
import br.com.mdr.testeandroid.flow.signin.SignInViewPresenter
import org.koin.dsl.module

val presenterModule = module {

    //SignIn
    single { SignInHandler(get(), get()) }
    single { SignInViewPresenter() }

    //Dashboard
    single { DashboardHandler(get()) }

}
package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.flow.dashboard.DashboardHandler
import br.com.mdr.testeandroid.flow.dashboard.DashboardViewPresenter
import br.com.mdr.testeandroid.flow.dashboard.IDashboardHandler
import br.com.mdr.testeandroid.flow.dashboard.IDashboardViewPresenter
import br.com.mdr.testeandroid.flow.signin.ISignInHandler
import br.com.mdr.testeandroid.flow.signin.ISignInViewPresenter
import br.com.mdr.testeandroid.flow.signin.SignInHandler
import br.com.mdr.testeandroid.flow.signin.SignInViewPresenter
import org.koin.dsl.module

val presenterModule = module {

    //SignIn
    single { SignInHandler(get(), get(), get()) as ISignInHandler }
    single { SignInViewPresenter() as ISignInViewPresenter }

    //Dashboard
    single { DashboardHandler(get(), get(), get()) as IDashboardHandler}
    single { DashboardViewPresenter() as IDashboardViewPresenter}

}
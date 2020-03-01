package com.lucianogiardino.bankapp.di

import android.content.SharedPreferences
import com.lucianogiardino.bankapp.login.LoginContract
import com.lucianogiardino.bankapp.login.LoginPresenter
import com.lucianogiardino.bankapp.login.domain.usecase.HasLoggedUserUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.LoginUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.SaveLoggedUserUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.ValidateUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module{

    factory<LoginContract.Presenter> { (view: LoginContract.View) ->
        LoginPresenter(view, get(), get(),get(),get())
     }

    single { ValidateUserUseCase() }
    single { LoginUseCase() }
    single {HasLoggedUserUseCase(androidContext())}
    single {SaveLoggedUserUseCase(androidContext())}



}
package com.lucianogiardino.bankapp.di

import android.content.Context
import com.lucianogiardino.bankapp.login.LoginContract
import com.lucianogiardino.bankapp.login.LoginPresenter
import com.lucianogiardino.bankapp.login.domain.usecase.LoginUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.ValidateUseCase
import org.koin.dsl.module

val appModule = module{

    factory<LoginContract.Presenter> { (view: LoginContract.View, context: Context) ->
        LoginPresenter(view, get(), get(),context)
     }

    single { ValidateUseCase() }
    single { LoginUseCase() }


}
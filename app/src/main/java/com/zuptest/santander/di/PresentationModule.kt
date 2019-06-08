package com.zuptest.santander.di

import com.zuptest.santander.login.LoginContract
import com.zuptest.santander.login.LoginPresenter
import com.zuptest.santander.statement.StatementsContract
import com.zuptest.santander.statement.StatementsPresenter
import org.koin.dsl.module

object PresentationModule {

    val module = module {

        factory<LoginContract.Presenter> { (view: LoginContract.View) ->
            LoginPresenter(
                view = view,
                doLoginUseCase = get()
            )
        }

        factory<StatementsContract.Presenter> { (view: StatementsContract.View) ->
            StatementsPresenter(
                view = view,
                listStatementsUseCase = get()
            )
        }
    }
}
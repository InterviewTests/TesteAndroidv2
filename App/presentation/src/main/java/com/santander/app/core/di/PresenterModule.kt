package com.santander.app.core.di

import com.santander.app.feature.login.LoginContract
import com.santander.app.feature.login.LoginPresenter
import com.santander.app.feature.statement.StatementContract
import com.santander.app.feature.statement.StatementPresenter
import org.koin.dsl.module.module

object PresenterModule {

    val module = module {

        factory<LoginContract.Presenter> { (view: LoginContract.View) ->
            LoginPresenter(
                view = view,
                loginUseCase = get(),
                getUserUseCase = get()
            )
        }

        factory<StatementContract.Presenter> { (view: StatementContract.View) ->
            StatementPresenter(
                view = view,
                cleanAccountUseCase = get(),
                getAccountUseCase = get(),
                statementUseCase = get()
            )
        }

    }
}
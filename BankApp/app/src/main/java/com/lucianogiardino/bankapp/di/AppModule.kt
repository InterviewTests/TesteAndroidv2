package com.lucianogiardino.bankapp.di

import com.lucianogiardino.bankapp.domain.usecase.*
import com.lucianogiardino.bankapp.ui.login.LoginContract
import com.lucianogiardino.bankapp.ui.login.LoginPresenter
import com.lucianogiardino.bankapp.ui.statement.StatementContract
import com.lucianogiardino.bankapp.ui.statement.StatementPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module{

    factory<LoginContract.Presenter> { (view: LoginContract.View) ->
        LoginPresenter(
            view,
            get(),
            get(),
            get(),
            get()
        )
     }

    factory<StatementContract.Presenter> { (view: StatementContract.View) ->
        StatementPresenter(
            view,
            get()
        )
    }

    single { ValidateUserUseCase() }
    single { LoginUseCase() }
    single<StatementContract.UseCase.FetchStatement> { FetchStatementUseCase() }
    single {
        HasLoggedUserUseCase(
            androidContext()
        )
    }
    single {
        SaveLoggedUserUseCase(
            androidContext()
        )
    }



}
package com.lucianogiardino.bankapp.di

import com.lucianogiardino.bankapp.data.repository.LoginRepository
import com.lucianogiardino.bankapp.data.repository.StatementRepository
import com.lucianogiardino.bankapp.domain.usecase.*
import com.lucianogiardino.bankapp.presentation.login.LoginContract
import com.lucianogiardino.bankapp.presentation.login.LoginPresenter
import com.lucianogiardino.bankapp.presentation.statement.StatementContract
import com.lucianogiardino.bankapp.presentation.statement.StatementPresenter
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
            get(),
            get()
        )
    }

    single<LoginContract.UseCase.ValidateUser> { ValidateUserUseCase() }
    single<LoginContract.UseCase.LoginUser> { LoginUseCase(get()) }
    single<LoginContract.Repository> { LoginRepository()}


    single<StatementContract.Repository> { StatementRepository()}
    single<StatementContract.UseCase.FetchStatement> { FetchStatementUseCase(get()) }
    single<LoginContract.UseCase.HasLoggedUser> {
        HasLoggedUserUseCase(
            androidContext()
        )
    }

    single<LoginContract.UseCase.SaveLoggedUser> {
        SaveLoggedUserUseCase(
            androidContext()
        )
    }

    single<StatementContract.UseCase.Logout>{
        LogoutUseCase(
            androidContext()
        )
    }



}
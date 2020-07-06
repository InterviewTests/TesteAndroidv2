package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.flow.error.ErrorListViewPresenter
import br.com.mdr.testeandroid.flow.error.ErrorViewPresenter
import br.com.mdr.testeandroid.flow.error.IErrorListViewPresenter
import br.com.mdr.testeandroid.flow.error.IErrorViewPresenter
import br.com.mdr.testeandroid.flow.signin.ISignInHandler
import br.com.mdr.testeandroid.flow.signin.ISignInViewPresenter
import br.com.mdr.testeandroid.flow.signin.SignInHandler
import br.com.mdr.testeandroid.flow.signin.SignInViewPresenter
import org.koin.dsl.module

val presenterModule = module {

    //SignIn
    single { SignInHandler(get(), get(), get()) as ISignInHandler }
    single { SignInViewPresenter() as ISignInViewPresenter }

    // Error
    single { ErrorViewPresenter() as IErrorViewPresenter }
    single { ErrorListViewPresenter() as IErrorListViewPresenter }

}
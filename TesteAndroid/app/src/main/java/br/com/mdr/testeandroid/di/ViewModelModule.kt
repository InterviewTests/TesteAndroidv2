package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.flow.DashboardViewModel
import br.com.mdr.testeandroid.flow.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Marlon D. Rocha
 * @since 05/07/2020
 */

val viewModelModule = module {
    viewModel { SignInViewModel(signInHandler = get(), signInViewPresenter = get()) }
    viewModel { DashboardViewModel() }
}
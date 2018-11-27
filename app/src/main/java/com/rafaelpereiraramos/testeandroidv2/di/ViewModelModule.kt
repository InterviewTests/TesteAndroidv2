package com.rafaelpereiraramos.testeandroidv2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafaelpereiraramos.testeandroidv2.core.ViewModelFactory
import com.rafaelpereiraramos.testeandroidv2.view.login.LoginActivityViewModel
import com.rafaelpereiraramos.testeandroidv2.view.statement.StatementsActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityViewModel::class)
    abstract fun bindLoginActivityViewModel(viewModel: LoginActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StatementsActivityViewModel::class)
    abstract fun bindStatementsActivityViewModel(viewModel: StatementsActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
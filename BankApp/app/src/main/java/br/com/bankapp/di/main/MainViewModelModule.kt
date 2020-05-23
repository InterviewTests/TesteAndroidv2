package br.com.bankapp.di.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.bankapp.di.login.LoginViewModelFactory
import br.com.bankapp.di.main.keys.MainViewModelKey
import br.com.bankapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: LoginViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @MainViewModelKey(MainViewModel::class)
    abstract fun bindMessageViewModel(mainViewModel: MainViewModel): ViewModel
}
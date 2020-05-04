package com.paulokeller.bankapp.di.module

import androidx.lifecycle.ViewModel
import com.paulokeller.bankapp.di.annotation.ViewModelKey
import com.paulokeller.bankapp.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: LoginViewModel) : ViewModel
}
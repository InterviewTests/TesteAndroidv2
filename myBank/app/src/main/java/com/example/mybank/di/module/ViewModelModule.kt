package com.example.mybank.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybank.ViewModelProviderFactory
import com.example.mybank.di.ViewModelKey
import com.example.mybank.screens.accountDetail.AccountDetailViewModel
import com.example.mybank.screens.login.LoginViewModel
import com.example.mybank.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountDetailViewModel::class)
    internal abstract fun bindAccountDetailViewModel(accountDetailViewModel: AccountDetailViewModel): ViewModel

    //bind da Factory
    @Binds
    internal abstract fun viewModelProviderFactory(vmProviderFactory: ViewModelProviderFactory<ViewModel>): ViewModelProvider.Factory
}

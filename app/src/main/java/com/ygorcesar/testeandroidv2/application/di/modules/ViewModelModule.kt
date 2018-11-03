package com.ygorcesar.testeandroidv2.application.di.modules

import android.arch.lifecycle.ViewModel
import com.ygorcesar.testeandroidv2.base.di.ViewModelKey
import com.ygorcesar.testeandroidv2.home.viewmodel.HomeViewModel
import com.ygorcesar.testeandroidv2.login.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(loginViewModel: HomeViewModel): ViewModel
}
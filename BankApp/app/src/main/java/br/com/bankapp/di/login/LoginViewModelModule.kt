package br.com.bankapp.di.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.bankapp.di.login.keys.LoginViewModelKey
import br.com.bankapp.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: LoginViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @LoginViewModelKey(LoginViewModel::class)
    abstract fun bindMessageViewModel(loginViewModel: LoginViewModel): ViewModel
}









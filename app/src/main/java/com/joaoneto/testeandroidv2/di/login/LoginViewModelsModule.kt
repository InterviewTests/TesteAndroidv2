package com.joaoneto.testeandroidv2.di.login

import androidx.lifecycle.ViewModel
import com.joaoneto.testeandroidv2.di.ViewModelKey
import com.joaoneto.testeandroidv2.loginscreen.viewModel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
 abstract class LoginViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}
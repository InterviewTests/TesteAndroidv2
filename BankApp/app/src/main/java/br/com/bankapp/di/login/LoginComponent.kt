package br.com.bankapp.di.login

import br.com.bankapp.ui.login.LoginActivity
import dagger.Subcomponent


@LoginScope
@Subcomponent(
    modules = [
        LoginViewModelModule::class
    ])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{

        fun create(): LoginComponent
    }

    fun inject(loginActivity: LoginActivity)
}








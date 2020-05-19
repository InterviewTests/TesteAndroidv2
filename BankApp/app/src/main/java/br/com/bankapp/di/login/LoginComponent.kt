package br.com.bankapp.di.login

import br.com.bankapp.ui.login.LoginActivity
import br.com.fortes.appcolaborador.di.main.LoginScope
import dagger.Subcomponent


@LoginScope
@Subcomponent(
    modules = [
        LoginModule::class,
        LoginViewModelModule::class
    ])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory{

        fun create(): LoginComponent
    }

    fun inject(loginActivity: LoginActivity)
}








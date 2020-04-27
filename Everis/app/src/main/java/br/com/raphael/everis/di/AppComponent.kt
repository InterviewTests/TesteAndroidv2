package br.com.raphael.everis.di

import br.com.raphael.everis.MainActivity
import br.com.raphael.everis.di.module.AppModule
import br.com.raphael.everis.di.module.BackendModule
import br.com.raphael.everis.di.module.RemoteModule
import br.com.raphael.everis.viewmodel.LoginViewModel
import br.com.raphael.everis.viewmodel.StatementsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        BackendModule::class,
        RemoteModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: StatementsViewModel)

}
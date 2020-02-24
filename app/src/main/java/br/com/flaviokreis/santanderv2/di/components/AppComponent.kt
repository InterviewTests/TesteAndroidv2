package br.com.flaviokreis.santanderv2.di.components

import br.com.flaviokreis.santanderv2.BankApplication
import br.com.flaviokreis.santanderv2.di.modules.ActivityModule
import br.com.flaviokreis.santanderv2.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class
    ]
)
interface AppComponent {
    fun inject(app: BankApplication)
}
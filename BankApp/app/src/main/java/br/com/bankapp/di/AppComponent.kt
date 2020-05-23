package br.com.bankapp.di

import android.content.Context
import br.com.bankapp.di.login.LoginComponent
import br.com.bankapp.di.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AppModule::class,
            SubComponentsModule::class
        ]
)
interface AppComponent  {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory

    fun mainComponent(): MainComponent.Factory
}
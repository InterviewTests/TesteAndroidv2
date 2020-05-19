package br.com.bankapp.di

import android.content.Context
import br.com.bankapp.di.login.LoginComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeAppModule::class,
        SubComponentsModule::class
    ]
)
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): TestAppComponent
    }
}
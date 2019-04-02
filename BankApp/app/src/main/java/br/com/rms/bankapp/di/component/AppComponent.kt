package br.com.rms.bankapp.di.component

import android.app.Application
import br.com.rms.MyApplication
import br.com.rms.bankapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        DataBaseModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class,
        PresenterModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(myApplication: MyApplication)
}
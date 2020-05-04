package com.paulokeller.bankapp.di.component

import android.app.Application
import android.content.SharedPreferences
import com.paulokeller.bankapp.BankAppApplication
import com.paulokeller.bankapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class,
        AppModule::class, ViewModelFactoryModule::class, ApiModule::class]
)
interface AppComponent : AndroidInjector<BankAppApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(@Named("baseUrl") baseUrl: String): Builder

        fun build(): AppComponent
    }

}
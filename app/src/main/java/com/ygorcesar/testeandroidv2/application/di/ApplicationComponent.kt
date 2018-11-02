package com.ygorcesar.testeandroidv2.application.di

import com.ygorcesar.testeandroidv2.application.di.modules.ApplicationModule
import com.ygorcesar.testeandroidv2.application.di.modules.NetworkModule
import com.ygorcesar.testeandroidv2.application.di.modules.ViewModelModule
import com.ygorcesar.testeandroidv2.application.di.scopes.ApplicationScope
import dagger.Component
import javax.inject.Singleton

/**
 * Function to get the current [ApplicationComponent] instance
 */
fun app() = ApplicationComponent.INSTANCE

@ApplicationScope
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {

    companion object {
        lateinit var INSTANCE: ApplicationComponent
    }
}

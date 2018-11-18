package com.rafaelpereiraramos.testeandroidv2.core

import com.rafaelpereiraramos.testeandroidv2.App
import com.rafaelpereiraramos.testeandroidv2.di.AppModule
import com.rafaelpereiraramos.testeandroidv2.di.DiskIOModule
import com.rafaelpereiraramos.testeandroidv2.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Singleton
@Component(modules = [AppModule::class, DiskIOModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder interface Builder {
        @BindsInstance fun application(@Named("appContext")app: App): Builder

        fun build(): AppComponent
    }

    /**
     * Inject Application class dependencies
     */
    fun inject(application: App)
}
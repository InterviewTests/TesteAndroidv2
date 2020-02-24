package br.com.flaviokreis.santanderv2.di.modules

import android.app.Application
import android.content.Context
import br.com.flaviokreis.santanderv2.BankApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module()
class AppModule(private val app: BankApplication) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.baseContext
    }
}
package br.com.raphael.everis.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import org.jetbrains.anko.defaultSharedPreferences

@Module
open class AppModule (private val app: Application) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    open fun provideContext() = app.applicationContext!!

    @Provides
    @Singleton
    open fun provideResources() = app.resources!!

    @Provides
    @Singleton
    open fun provideSharedPreferences() = app.defaultSharedPreferences

}
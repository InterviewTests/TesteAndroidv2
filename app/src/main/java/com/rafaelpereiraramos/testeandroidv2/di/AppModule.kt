package com.rafaelpereiraramos.testeandroidv2.di

import android.preference.PreferenceManager
import com.rafaelpereiraramos.testeandroidv2.App
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
@Module(includes = [AndroidInjectionModule::class, ActivityModule::class, ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(@Named("appContext")app: App) = app

    @Provides
    @Singleton
    fun provideSharedPreferences(app: App) = PreferenceManager.getDefaultSharedPreferences(app)!!
}
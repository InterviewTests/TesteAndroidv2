package com.danjdt.testeandroidv2.di.module

import com.danjdt.testeandroidv2.BankApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: BankApplication) {

    @Provides
    @Singleton
    fun provideApp() = app
}
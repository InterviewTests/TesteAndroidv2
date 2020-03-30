package com.example.base.di

import com.example.bankapp.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    @Singleton
    @Named("isDebuggable")
    fun provideIsDebuggable() = BuildConfig.DEBUG

    companion object{
        const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
    }
}
package com.joaoneto.testeandroidv2.di.main

import com.joaoneto.testeandroidv2.mainscreen.service.StatementsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    @Provides
    fun provideLogin(retrofit: Retrofit): StatementsService {
        return retrofit.create(StatementsService::class.java)
    }
}
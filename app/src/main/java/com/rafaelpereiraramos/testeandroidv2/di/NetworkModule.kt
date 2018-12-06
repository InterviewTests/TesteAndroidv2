package com.rafaelpereiraramos.testeandroidv2.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rafaelpereiraramos.testeandroidv2.App
import com.rafaelpereiraramos.testeandroidv2.api.BankApiService
import com.rafaelpereiraramos.testeandroidv2.api.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 18/11/2018.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideBankApiService(gson: Gson, httpClient: OkHttpClient): BankApiService {
        val builder = Retrofit.Builder()
            .baseUrl(App.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

        return builder.create(BankApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat(App.DATE_FORMAT)
            .create()
    }

    @Provides
    @Singleton
    fun provideOkkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.d(it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
}
package com.rafaelpereiraramos.testeandroidv2.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rafaelpereiraramos.testeandroidv2.App
import com.rafaelpereiraramos.testeandroidv2.api.BankApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 18/11/2018.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGithubService(gson: Gson, httpClient: OkHttpClient): BankApi {
        val builder = Retrofit.Builder()
            .baseUrl(App.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return builder.create(BankApi::class.java)
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
            Log.d("API", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
}
package com.zuptest.santander.di

import com.zuptest.data.api.Api
import com.zuptest.santander.BuildConfig
import com.zuptest.santander.BuildConfig.DEBUG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    val module = module {

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
        }

        single<Api> {
            (get() as Retrofit).create(Api::class.java)
        }

        single<OkHttpClient> {
            val interceptor = HttpLoggingInterceptor()
            if (DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
    }
}
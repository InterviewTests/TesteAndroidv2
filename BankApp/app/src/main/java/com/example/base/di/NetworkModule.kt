package com.example.base.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("baseUrl") baseUrl: String,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    @JvmSuppressWildcards
    fun provideOkHttpClient(interceptors: Set<Interceptor>): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        interceptors.forEach {
            okHttpBuilder.addInterceptor(it)
        }
        return okHttpBuilder.build()
    }


    @Singleton
    @IntoSet
    @Provides
    fun provideLoggingInterceptor(@Named("isDebuggable") debug: Boolean): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (debug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

}
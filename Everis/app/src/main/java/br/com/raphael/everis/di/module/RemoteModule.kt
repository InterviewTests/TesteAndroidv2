package br.com.raphael.everis.di.module

import br.com.raphael.everis.remote.util.PrintingEventListener
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class RemoteModule {

    @Provides
    @Singleton
    open fun providesOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .protocols(arrayListOf(Protocol.HTTP_1_1))
            .eventListenerFactory {
                PrintingEventListener(
                    PrintingEventListener.nextCallId.getAndIncrement(),
                    System.nanoTime()
                )
            }

    @Provides
    @Singleton
    open fun provideOkHttp(
        builder: OkHttpClient.Builder, logging: HttpLoggingInterceptor
    ): OkHttpClient {

        val b = builder

        return b.addInterceptor { chain ->
            var requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Cache-Control", "private, max-age=1")

            chain.proceed(requestBuilder.build())
        }
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}
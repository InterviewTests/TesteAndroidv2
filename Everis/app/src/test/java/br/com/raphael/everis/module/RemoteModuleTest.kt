package br.com.raphael.everis.module

import br.com.raphael.everis.di.module.RemoteModule
import br.com.raphael.everis.remote.util.PrintingEventListener
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class RemoteModuleTest() : RemoteModule() {

    override fun providesOkHttpClientBuilder(): OkHttpClient.Builder =
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

    override fun provideOkHttp(
        builder: OkHttpClient.Builder,
        logging: HttpLoggingInterceptor
    ): OkHttpClient = builder.build()

}
package com.example.desafiosantander.di

import com.example.desafiosantander.BuildConfig
import com.example.desafiosantander.data.api.ApiService
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "BASE_URL"
private const val CONNECTION_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L
private const val APP_VERSION = "app-version"

val networkModule = module {

    single {
        val httpLogInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLogInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLogInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val tokenInterceptor = Interceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .header(APP_VERSION, BuildConfig.VERSION_CODE.toString())
                .build()
            chain.proceed(newRequest)
        }

        OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLogInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(getProperty<String>(BASE_URL))
            .client(get())
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create<ApiService>(ApiService::class.java)
    }

}
package com.androiddeveloper.santanderTest.shared.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

abstract class BaseClient {

    val gson: Gson
    val okHttpClient: OkHttpClient

    init {
        gson = createGson()

        okHttpClient = createHttpClient()
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    private fun createGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create()
    }
}
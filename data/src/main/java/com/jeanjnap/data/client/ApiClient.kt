package com.jeanjnap.data.client

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jeanjnap.data.R
import com.jeanjnap.data.util.adapter.BigDecimalAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    inline fun <reified T> makeService(context: Context): T {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply { level = getLogLevel() })

        }.build().let { retrofitCreate(context.getString(R.string.base_url), it) }
    }

    inline fun <reified T> retrofitCreate(baseUrl: String, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshiFactory()).asLenient())
            .build()
        return retrofit.create(T::class.java)
    }

    fun getLogLevel() = HttpLoggingInterceptor.Level.BODY

    fun moshiFactory(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(BigDecimalAdapter())
        .build()
}
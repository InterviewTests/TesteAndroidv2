package com.androiddeveloper.santanderTest.shared.network

import com.androiddeveloper.santanderTest.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Factory : BaseClient() {

    fun <T> buildApi(endpoint: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(endpoint)
    }

    fun <T> buildMockApi(endpoint: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_MOCK)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(endpoint)
    }
}
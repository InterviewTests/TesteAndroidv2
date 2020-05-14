package com.example.testeandroideveris.data.api

import com.example.testeandroideveris.feature.login.data.api.LoginAPI
import com.example.testeandroideveris.feature.statements.data.api.StatementAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitBuilder {

    const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"

    inline fun <reified T> getRetrofit(): T {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(logging)

        val builder =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
        return builder.create(T::class.java)
    }
}
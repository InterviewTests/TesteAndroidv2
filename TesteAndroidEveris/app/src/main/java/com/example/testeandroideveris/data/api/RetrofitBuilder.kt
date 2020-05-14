package com.example.testeandroideveris.data.api

import com.example.testeandroideveris.feature.login.data.api.LoginAPI
import com.example.testeandroideveris.feature.statements.data.api.StatementAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"

    private fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val apiService: LoginAPI = getRetrofit()
        .create(LoginAPI::class.java)

    val apiStatement: StatementAPI = getRetrofit()
        .create(StatementAPI::class.java)
}
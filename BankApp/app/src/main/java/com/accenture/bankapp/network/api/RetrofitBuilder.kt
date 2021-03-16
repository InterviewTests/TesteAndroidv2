package com.accenture.bankapp.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitBuilder {
    private const val API_URL = "https://bank-app-test.herokuapp.com/api/"

    private fun getRetrofit(): Retrofit {
        Timber.i("getRetrofit: Building Retrofit with URL $API_URL")

        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}
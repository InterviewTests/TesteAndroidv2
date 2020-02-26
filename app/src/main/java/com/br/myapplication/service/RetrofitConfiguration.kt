package com.br.myapplication.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfiguration {

    companion object {
        const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
    }

    private fun getRetrofit() =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()

    fun getAppRequest(): Requests = getRetrofit().create(Requests::class.java)
}
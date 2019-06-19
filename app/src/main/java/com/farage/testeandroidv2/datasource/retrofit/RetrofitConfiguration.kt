package com.farage.testeandroidv2.datasource.retrofit

import com.farage.testeandroidv2.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfiguration {

    val instance: Retrofit by lazy {
        return@lazy Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
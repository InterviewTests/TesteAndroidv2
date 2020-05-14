package com.example.testeandroideveris.data.api

import com.example.testeandroideveris.feature.login.data.api.LoginAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val apiService: LoginAPI = getRetrofit()
        .create(LoginAPI::class.java)
}
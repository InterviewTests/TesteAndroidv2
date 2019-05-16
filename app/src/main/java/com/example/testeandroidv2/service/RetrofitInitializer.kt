package com.example.testeandroidv2.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl(endpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createLoginService() = retrofit.create(LoginService::class.java)!!

    companion object {
        private const val endpoint = "https://bank-app-test.herokuapp.com/api/"
    }
}
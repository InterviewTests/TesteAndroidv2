package com.example.testeandroidv2.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl(endpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val services = retrofit.create(Services::class.java)

    fun loginService() = services!!

    fun statementService() = services!!

    companion object {
        private const val endpoint = "https://bank-app-test.herokuapp.com/api/"
    }
}
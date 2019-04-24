package com.example.testeandroidsantander.network

import com.example.testeandroidsantander.api.StatementApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer(url : String) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun statementService() = retrofit.create(StatementApiService::class.java)
}
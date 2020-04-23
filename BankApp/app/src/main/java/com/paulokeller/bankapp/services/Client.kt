package com.paulokeller.bankapp.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field

import retrofit2.http.POST
import java.io.IOException

object Client {
    private const val API_URL = "https://bank-app-test.herokuapp.com"
    var  instance:Services? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val client = retrofit.create(Services::class.java)
        instance = client
    }
}
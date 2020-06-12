package com.appdesafioSantander.services

import com.appdesafioSantander.bank.model.Login
import com.appdesafioSantander.bank.services.ApiService.Companion.url
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiData {

    @GET("statements/0")
    fun getStatements(): Call<Map<String, Any>>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body loginData: Map<String, String>): Call<Login>

    companion object {
        operator fun invoke(): ApiData {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiData::class.java)
        }
    }
}


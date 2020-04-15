package com.br.bankapp.api

import com.br.bankapp.model.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface BankApi {

    @GET("statements/0")
    fun getStatements(): Call<Map<String, Any>>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body loginData: Map<String, String>) : Call<LoginResponse>

    companion object {
        operator fun invoke(): BankApi {
            return Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BankApi::class.java)
        }
    }
}
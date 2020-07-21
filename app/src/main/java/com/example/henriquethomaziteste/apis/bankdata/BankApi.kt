package com.example.henriquethomaziteste.apis.bankdata

import retrofit2.Call
import retrofit2.http.*

interface BankApi {

    @POST("login")
    fun login(@Body loginData: BankLoginRequest): Call<BankLoginResponse>

    @GET("statements/{id}")
    fun statements(@Path("id") id: String): Call<BankResponse>
}
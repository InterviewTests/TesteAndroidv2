package com.example.mybank.data.remote

import com.example.mybank.data.remote.model.RecordTransactionResponse
import com.example.mybank.data.remote.model.RecordUserResponse
import com.example.mybank.data.remote.model.RequestLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyBankApi {

    @POST("login")
    fun login(@Body body: RequestLogin): Call<RecordUserResponse>

    @GET("statements/{userId}")
    fun getUserTransactions(@Path("userId") userId: Int): Call<RecordTransactionResponse>
}
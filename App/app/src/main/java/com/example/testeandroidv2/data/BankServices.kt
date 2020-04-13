package com.example.testeandroidv2.data

import com.example.testeandroidv2.domain.login.LoginBodyResponse
import com.example.testeandroidv2.domain.statements.StatementsBodyResponse
import com.example.testeandroidv2.domain.login.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankServices {

    @POST("login")
    fun login(@Body user: User): Call<LoginBodyResponse>

    @GET("statements/{id}")
    fun getStatements(@Path("id") id: Int): Call<StatementsBodyResponse>

}
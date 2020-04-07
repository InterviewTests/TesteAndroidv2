package com.example.testeandroidv2.data

import com.example.testeandroidv2.model.StatementsBodyResponse
import com.example.testeandroidv2.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BankServices {

    @POST("login")
    fun login(@Body user: User?): Call<User?>?

    @GET("statements/1")
    fun getStatements(): Call<StatementsBodyResponse>

}
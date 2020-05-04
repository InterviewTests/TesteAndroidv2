package com.paulokeller.bankapp.services

import android.app.Application
import com.paulokeller.bankapp.models.Account
import com.paulokeller.bankapp.models.Statements
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import java.io.IOException

interface Client {
    @POST("/api/login")
    fun login(@Body userDTO: UserDTO): Call<Account>

    @GET("/api/statements/{page}")
    fun fetchStatements(@Path("page") page: Int): Call<Statements>?
}

class UserDTO(val user:String, val password:String)
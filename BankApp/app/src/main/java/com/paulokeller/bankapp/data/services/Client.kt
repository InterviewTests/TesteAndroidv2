package com.paulokeller.bankapp.data.services

import com.paulokeller.bankapp.data.models.Account
import com.paulokeller.bankapp.data.models.Statements
import com.paulokeller.bankapp.data.models.UserDTO
import retrofit2.Call
import retrofit2.http.*

interface Client {
    @POST("/api/login")
    fun login(@Body userDTO: UserDTO): Call<Account>

    @GET("/api/statements/{page}")
    fun fetchStatements(@Path("page") page: Int): Call<Statements>?
}


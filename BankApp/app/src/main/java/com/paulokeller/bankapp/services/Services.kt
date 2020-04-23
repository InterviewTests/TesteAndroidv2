package com.paulokeller.bankapp.services


import com.paulokeller.bankapp.models.Account
import com.paulokeller.bankapp.models.Statements
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface Services {
    @POST("/api/login")
    fun login(@Body userDTO: UserDTO): Call<Account>

    @GET("/api/statements/{page}")
    fun fetchStatements(@Path("page") page: Int): Call<Statements>?
}

class UserDTO(val user:String, val password:String)
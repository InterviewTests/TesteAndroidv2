package com.jisellemartins.bank.service

import com.jisellemartins.bank.model.DetailsUser
import com.jisellemartins.bank.model.Login
import com.jisellemartins.bank.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankService {
    @POST("api/bank/login")
    suspend fun postLogin(@Body login: Login): Response<User>

    @GET("api/bank/login/{idUser}/statements")
    suspend fun getDetailsUser(@Path("idUser")idUser:String): Response<List<DetailsUser>>
}
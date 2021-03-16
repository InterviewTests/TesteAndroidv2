package com.accenture.bankapp.network.api

import com.accenture.bankapp.network.models.LoginResponse
import com.accenture.bankapp.network.models.StatementsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("Login")
    suspend fun requestLogin(@Field("user") user: String, @Field("password") password: String): Response<LoginResponse>

    @GET("statements/{userId}")
    suspend fun getStatements(@Path("userId") userId: Int): Response<StatementsResponse>
}
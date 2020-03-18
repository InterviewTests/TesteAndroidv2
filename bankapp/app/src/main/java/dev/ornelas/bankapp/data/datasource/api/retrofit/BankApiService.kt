package dev.ornelas.bankapp.data.datasource.api.retrofit

import dev.ornelas.bankapp.data.datasource.api.retrofit.model.LoginResponseApi
import dev.ornelas.bankapp.data.datasource.api.retrofit.model.StatementApi
import dev.ornelas.bankapp.data.datasource.api.retrofit.model.StatementResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankApiService {

    @POST("login")
    suspend fun login(
        @Field("user") user: String,
        @Field("password") password: String
    ): LoginResponseApi

    @GET("statements/{userId}")
    suspend fun getStatements(@Path("userId") userId: String): StatementResponse
}
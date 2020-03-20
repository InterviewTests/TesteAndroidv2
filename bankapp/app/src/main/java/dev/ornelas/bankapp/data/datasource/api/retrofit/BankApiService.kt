package dev.ornelas.bankapp.data.datasource.api.retrofit

import dev.ornelas.bankapp.data.datasource.api.retrofit.model.LoginResponseApi
import dev.ornelas.bankapp.data.datasource.api.retrofit.model.StatementApi
import dev.ornelas.bankapp.data.datasource.api.retrofit.model.StatementResponse
import retrofit2.http.*

interface BankApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("user") user: String,
        @Field("password") password: String
    ): LoginResponseApi

    @GET("statements/{userId}")
    suspend fun getStatements(@Path("userId") userId: String): StatementResponse
}
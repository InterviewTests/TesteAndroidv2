package br.com.bankapp.data.api

import br.com.bankapp.data.api.network_responses.LoginResponse
import br.com.bankapp.data.api.network_responses.StatementListResponse
import retrofit2.http.*

interface BankAppApiService {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("user") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("statements/{userId}")
    suspend fun loadStatements(@Path("userId") userId: Int): StatementListResponse
}
package br.com.bankapp.data.api

import br.com.bankapp.data.api.network_responses.LoginResponse
import br.com.bankapp.data.api.network_responses.StatementListResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BankAppApiService {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("user") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("account/properties")
    suspend fun getStatements(): StatementListResponse
}
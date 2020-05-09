package br.com.raphael.everis.remote

import br.com.raphael.everis.model.Response
import br.com.raphael.everis.model.StatementList
import retrofit2.http.*

interface BackendService {

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("user") user: String,
        @Field("password") password: String
    ): Response

    @GET("statements/{userId}")
    suspend fun getStatement(
        @Path("userId") userId: Int
    ): StatementList
}
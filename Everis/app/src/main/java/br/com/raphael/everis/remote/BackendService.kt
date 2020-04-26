package br.com.raphael.everis.remote

import br.com.raphael.everis.model.Statement
import br.com.raphael.everis.model.UserAccount
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BackendService {
    @POST("login")
    suspend fun postLogin(): UserAccount

    @GET("statements/{userId}")
    suspend fun getStatement(
        @Path("userId") userId: Int
    ): Statement
}
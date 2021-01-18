package com.jeanjnap.data.source.remote.service

import com.jeanjnap.data.model.response.StatementSummaryResponse
import com.jeanjnap.data.model.response.UserDataResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankService {

    @FormUrlEncoded
    @POST("login")
    fun loginAsync(
        @Field("user") user: String,
        @Field("password") password: String
    ): Deferred<Response<UserDataResponse>>

    @GET("statements/{userId}")
    fun getStatementsAsync(
        @Path("userId") userId: Long?
    ): Deferred<Response<StatementSummaryResponse>>
}
package dev.vitorpacheco.solutis.bankapp.api

import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginResponse
import dev.vitorpacheco.solutis.bankapp.statementsScreen.StatementsResponse
import retrofit2.Call
import retrofit2.http.*


interface BankApi {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") password: String): Call<LoginResponse>

    @GET("statements/{userId}")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun listStatements(@Path("userId") userId: Int): Call<StatementsResponse>

}

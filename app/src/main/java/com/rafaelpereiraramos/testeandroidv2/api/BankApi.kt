package com.rafaelpereiraramos.testeandroidv2.api

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import retrofit2.http.*

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
interface BankApi {

    @FormUrlEncoded
    //@Headers(value = ["Content-Type: application/x-www-form-urlencoded"])
    @POST("login/")
    fun login(
        @Field("user")user: String,
        @Field("password")password: String
    ): ApiResponseLiveData<LoginResponse>

    @GET("statements/{id}")
    fun getStatements(@Path("id") userId: Int): ApiResponseLiveData<StatementsResponse>
}
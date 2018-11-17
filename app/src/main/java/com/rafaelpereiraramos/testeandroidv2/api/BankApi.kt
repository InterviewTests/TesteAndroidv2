package com.rafaelpereiraramos.testeandroidv2.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
interface BankApi {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("userName")userName: String,
        @Field("password")password: String
    )
}
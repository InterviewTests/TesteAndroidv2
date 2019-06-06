package com.zuptest.data.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("login")
    fun doLogin()

    @GET("statements/{idUser}")
    fun listStatementsByUserId(@Path("idUser") userId: String)

}
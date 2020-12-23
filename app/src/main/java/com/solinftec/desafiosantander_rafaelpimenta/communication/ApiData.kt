package com.solinftec.desafiosantander_rafaelpimenta.communication

import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse
import com.solinftec.desafiosantander_rafaelpimenta.model.StatementResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiData {

    @POST("login")
    fun login(@Body loginData: Map<String, String>): Call<LoginResponse>

    @GET("statements/{idUser}")
    fun getStatements(
        @Path("idUser") idUser: Long
    ): Call<StatementResponse>
}
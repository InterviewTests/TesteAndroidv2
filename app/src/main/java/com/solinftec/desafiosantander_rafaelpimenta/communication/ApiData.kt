package com.solinftec.desafiosantander_rafaelpimenta.communication

import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse
import com.solinftec.desafiosantander_rafaelpimenta.model.StatementResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiData {

    @GET("login")
    fun genres(): Single<LoginResponse>

    @GET("statements/{idUser}")
    fun upcomingMovies(
        @Path("idUser") idUser: Long
    ): Single<StatementResponse>
}
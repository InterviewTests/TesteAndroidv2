package com.tata.bank.network

import com.tata.bank.model.LoginResponse
import com.tata.bank.model.StatementResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("login")
    fun genres(): Single<LoginResponse>

    @GET("statements/{idUser}")
    fun upcomingMovies(
        @Path("idUser") idUser: Long
    ): Single<StatementResponse>
}
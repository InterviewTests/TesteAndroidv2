package com.br.rafael.TesteAndroidv2.data.api

import com.br.rafael.TesteAndroidv2.data.model.StatementResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementApi {

    @GET("statements/{idUser}")
    fun getStatements(@Path("idUser") idUser : Long) : Call<StatementResponse>
}
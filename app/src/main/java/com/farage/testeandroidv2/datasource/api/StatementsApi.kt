package com.farage.testeandroidv2.datasource.api

import com.farage.testeandroidv2.datasource.model.StatementRequest
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementsApi {

    @GET("statements/{id}")
    fun getAllStatements(@Path("id") id: Int): Deferred<StatementRequest>

}
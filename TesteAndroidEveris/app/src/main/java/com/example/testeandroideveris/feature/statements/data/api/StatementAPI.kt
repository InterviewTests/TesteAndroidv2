package com.example.testeandroideveris.feature.statements.data.api

import com.example.testeandroideveris.feature.statements.data.StatementList
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementAPI {

    @GET("statements/{userId}")
    suspend fun getStatements(@Path("userId") userId: Int): StatementList

}
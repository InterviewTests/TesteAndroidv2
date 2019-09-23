package com.gustavo.bankandroid.statements.repository.api

import com.gustavo.bankandroid.statements.data.gson.StatementResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerCalls {

    @GET("statements/{idUser}")
    fun fetchStatements(@Path("idUser") id: Int): Single<StatementResponse>

}
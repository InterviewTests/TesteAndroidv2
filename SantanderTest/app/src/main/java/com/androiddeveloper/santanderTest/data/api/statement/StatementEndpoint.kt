package com.androiddeveloper.santanderTest.data.api.statement

import com.androiddeveloper.santanderTest.data.model.statements.Statements
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementEndpoint {

    @GET("statements/{id}")
    fun getStatements(@Path("id") id: Int): Flowable<Response<Statements>>
}
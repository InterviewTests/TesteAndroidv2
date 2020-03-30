package com.example.bankapp.features.details.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementService {
    @GET("statements/{id}")
    fun getStatementService(@Path("id") id: String): Single<Statements?>
}


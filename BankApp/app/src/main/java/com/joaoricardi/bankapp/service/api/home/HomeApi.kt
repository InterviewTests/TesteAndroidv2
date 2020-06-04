package com.joaoricardi.bankapp.service.api.home

import com.joaoricardi.bankapp.models.home.StatementResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HomeApi {

    @GET("statements/1")
    fun getStatements(): Deferred<StatementResponse>
}
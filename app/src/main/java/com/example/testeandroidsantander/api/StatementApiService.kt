package com.example.testeandroidsantander.api

import com.example.testeandroidsantander.model.Statement
import com.example.testeandroidsantander.model.StatementList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementApiService {
    companion object {
        val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
    }

    @GET("statements/1/")
    fun getEStatement( ): Call<StatementList>


}
package com.example.testeandroidsantander.api

import com.example.testeandroidsantander.model.Statement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

abstract class LoginApiService {
    companion object {
        val BASE_URL = "https://bank-app-test.herokuapp.com/api/login"
    }

    @GET("{statementList}/json/")
    abstract fun getEStatement(@Path("statementList") CEP: String): Call<Statement>

}
package com.example.testeandroidv2

import com.example.testeandroidv2.login.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl(endpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun requestLogin() = retrofit.create(LoginService::class.java)!!

    companion object {
        private const val endpoint = "https://bank-app-test.herokuapp.com/api/"
    }
}
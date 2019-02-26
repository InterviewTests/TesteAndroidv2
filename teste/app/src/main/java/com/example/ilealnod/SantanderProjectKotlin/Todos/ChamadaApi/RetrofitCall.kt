package com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCall {

// Chamada da api E implementação do RETROFIT

    private var BASE_URL: String = "https://bank-app-test.herokuapp.com/api/"
    private var retrofit: Retrofit? = null

    fun getCall(): Retrofit {

        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

}

package com.example.appbanksantander_accenturetalentacquisition.API

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var BASE_URL = "https://bank-app-test.herokuapp.com/"
    private var retrofit: Retrofit? = null

    val gson: Gson = GsonBuilder().create()

    fun getClient(): Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }
        return this.retrofit!!
    }
}
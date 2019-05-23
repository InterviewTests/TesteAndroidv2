package com.example.testesantander.data

import com.example.testesantander.data.service.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig{

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bank-app-test.herokuapp.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun service() : Service{
        return retrofit.create(Service::class.java)
    }
}
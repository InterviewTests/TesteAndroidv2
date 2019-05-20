package com.felipemsa.idletime.data

import com.felipemsa.idletime.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BankApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.HOST_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
        .build()

    fun banckApi() = retrofit.create(BankService::class.java)
}
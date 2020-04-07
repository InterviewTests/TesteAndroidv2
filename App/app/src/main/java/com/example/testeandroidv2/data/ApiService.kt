package com.example.testeandroidv2.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private fun initRetroFit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://bank-app-test.herokuapp.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service = initRetroFit().create(BankServices::class.java)

}
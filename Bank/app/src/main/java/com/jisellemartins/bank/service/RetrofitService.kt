package com.jisellemartins.bank.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object service {
    val api
        get() = Retrofit.Builder()
            .baseUrl("https://651f39d044a3a8aa476987bb.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    val bankService = api.create(BankService::class.java)
}

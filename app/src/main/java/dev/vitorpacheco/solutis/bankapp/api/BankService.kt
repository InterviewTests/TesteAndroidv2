package dev.vitorpacheco.solutis.bankapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BankService {
    fun create(): BankApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://bank-app-test.herokuapp.com/api/")
            .build()

        return retrofit.create(BankApi::class.java)
    }
}
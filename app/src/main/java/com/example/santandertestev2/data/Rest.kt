package com.example.santandertestev2.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Rest {

    private const val BASE_URL = "https://bank-app-test.herokuapp.com"
    private var retrofit: Retrofit? = null

    fun getRetrofitInstance(): Retrofit? {

        if (this.retrofit == null) {
            this.retrofit = Retrofit.Builder()
                .baseUrl(this.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}
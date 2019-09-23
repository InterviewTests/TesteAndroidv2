package com.example.santantest.data

import com.example.santantest.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {

    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit? {

        if (this.retrofit == null) {
            this.retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }

}
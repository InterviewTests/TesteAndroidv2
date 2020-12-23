package com.solinftec.desafiosantander_rafaelpimenta.communication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class ApiService {

    private fun retrofitBuilder(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val api: ApiData = retrofitBuilder()
        .create(ApiData::class.java)

    companion object {
        const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
    }
}
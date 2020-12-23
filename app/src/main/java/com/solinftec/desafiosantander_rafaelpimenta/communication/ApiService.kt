package com.solinftec.desafiosantander_rafaelpimenta.communication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

abstract class  ApiService{
    companion object {
        const val url = "https://bank-app-test.herokuapp.com/api/"
    }
}
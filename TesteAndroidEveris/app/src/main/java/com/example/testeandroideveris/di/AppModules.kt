package com.example.testeandroideveris.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val URL_API = "https://bank-app-test.herokuapp.com/api"

val appModules = module {

    
}

inline fun <reified T> createAPIService(baseUrl: String): T {

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}
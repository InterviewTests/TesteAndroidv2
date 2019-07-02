package io.github.maikotrindade.bankapp.base.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseNetwork {

    private const val baseUrl = "https://bank-app-test.herokuapp.com/api/"

    fun <T> get(clazz: Class<T>): T {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(OkHttpClient().newBuilder().build())
            .build()

        return retrofit.create(clazz)
    }

}
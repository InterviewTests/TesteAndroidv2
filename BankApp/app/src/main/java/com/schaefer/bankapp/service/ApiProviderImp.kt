package com.schaefer.bankapp.service

import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiProviderImp {
    @JvmStatic
    private val server = "https://bank-app-test.herokuapp.com/api/"
    @JvmStatic
    private var retrofit: Retrofit? = null
    @JvmStatic
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    @JvmStatic
    val connection: Retrofit
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(0, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(server)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
            }
            return retrofit!!
        }
}
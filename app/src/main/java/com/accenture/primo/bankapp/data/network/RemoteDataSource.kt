package com.accenture.primo.bankapp.data.network

import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    private const val API_BASE_URL = "https://bank-app-test.herokuapp.com/api/"
    private const val TIME_OUT_SECONDS = 10L
    private val _HTTPLogging = HttpLoggingInterceptor()
    private val _retrofit: Retrofit

    init {
        this._retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(getHtppClient())
            .build()
    }

    private fun getHtppClient(): OkHttpClient {
        var objHTTP = OkHttpClient.Builder().connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
        objHTTP.addInterceptor(_HTTPLogging.setLevel(HttpLoggingInterceptor.Level.BODY))

        return objHTTP.build()
    }

    internal fun getService(): IServices {
        return _retrofit.create(IServices::class.java)
    }
}
package com.example.androidtest.api

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceManager {

    val TAG: String = this::class.java.simpleName


    fun getApi(): Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getOkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getHttpInterceptor())
        .build()

    private fun getHttpInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

}

//@SuppressLint("CheckResult")
//fun <T> Observable<T>.serviceCall(onResponse: (T) -> Unit, onThrow: (Throwable) -> Unit) {
//    observeOn(AndroidSchedulers.mainThread())
//        .subscribeOn(Schedulers.io())
//        .subscribe(onResponse, onThrow)
//}

@SuppressLint("CheckResult")
fun <T> Observable<T>.serviceCall(onResponse: (T) -> Unit, onThrow: (Throwable) -> Unit) {
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({
            try {
                onResponse(it)
            } catch (e: Exception) {
                onThrow(ServiceResponseException(e))
            }
        }, {
            onThrow(it)
        })
}

class ServiceResponseException(cause: Throwable? = null) : Throwable(cause)
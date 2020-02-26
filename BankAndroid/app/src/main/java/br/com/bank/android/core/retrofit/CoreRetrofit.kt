package br.com.bank.android.core.retrofit

import br.com.bank.android.core.retrofit.auth.BankAuth
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoreRetrofit {

    private var BASE_URL = " https://bank-app-test.herokuapp.com/api/"
    private val gson = GsonBuilder().setLenient().create()

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getBankAuth() = getRetrofit().create(
        BankAuth::class.java
    )
}
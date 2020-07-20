package com.example.henriquethomaziteste.apis.bankdata

import com.example.henriquethomaziteste.events.BankLoginEvent
import com.example.henriquethomaziteste.events.BankStatementsEvent
import com.example.henriquethomaziteste.helper.EventBus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

interface BankApiManager {

    var retrofitClient: Retrofit
        get() = getRetrofitInstance("https://bank-app-test.herokuapp.com/api/")
        set(value) {}

    var endpoint: BankApi
        get() = retrofitClient.create(BankApi::class.java)
        set(value) {}

    fun login(user: String, pass: String){
        endpoint.login(BankLoginRequest(user, pass)).enqueue(object: Callback<BankLoginResponse> {
            override fun onFailure(call: Call<BankLoginResponse>, t: Throwable) {
                EventBus.post(BankLoginEvent(true, null))
            }

            override fun onResponse(
                call: Call<BankLoginResponse>,
                response: Response<BankLoginResponse>
            ) {
                if (response.body()?.error != null) {
                    EventBus.post(BankLoginEvent(false, response.body()?.userAccount))
                }
            }

        })
    }

    fun getStatements(id: String){
        endpoint.statements(id).enqueue(object: Callback<BankResponse> {
            override fun onFailure(call: Call<BankResponse>, t: Throwable) {
                EventBus.post(BankStatementsEvent(true, null))
            }

            override fun onResponse(call: Call<BankResponse>, response: Response<BankResponse>) {
                if (response.body()?.error != null){
                    EventBus.post(BankStatementsEvent(false, response.body()?.statementList))
                }
            }


        })
    }

    fun getRetrofitInstance(path: String): Retrofit {

        var client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(path)
            .addConverterFactory(
                MoshiConverterFactory.create(
                Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()).asLenient())
            .build()
    }
}
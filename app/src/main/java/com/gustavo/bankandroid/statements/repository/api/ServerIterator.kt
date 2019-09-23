package com.gustavo.bankandroid.statements.repository.api

import com.google.gson.Gson
import com.gustavo.bankandroid.statements.data.gson.StatementResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServerIterator {

    private val serverCalls: ServerCalls

    companion object {
        const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
    }

    init {
        val clientBuilder = OkHttpClient.Builder()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        serverCalls = retrofit
            .create(ServerCalls::class.java)
    }

    fun fetchStatements(id: Int): Single<StatementResponse> {
        return serverCalls.fetchStatements(id)
    }

}
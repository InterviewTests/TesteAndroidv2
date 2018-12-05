package com.br.natanfelipe.bankapp.api

import com.br.natanfelipe.bankapp.BuildConfig
import com.br.natanfelipe.bankapp.model.Statement
import com.br.natanfelipe.bankapp.model.StatementList
import com.br.natanfelipe.bankapp.model.User
import com.br.natanfelipe.bankapp.model.UserAccount
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestApi {

    val bankApi: BankApi
    val contentType = "application/x-www-form-urlencoded"

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        bankApi = retrofit.create<BankApi>(BankApi::class.java)
    }

    fun doLogin(user: String, pass: String): Observable<com.br.natanfelipe.bankapp.model.Response>? {
        return bankApi.doLogin(contentType, user, pass)
                .map { response -> com.br.natanfelipe.bankapp.model.Response(response.userAccount,response.error)}
    }

    fun loadBills(): Observable<StatementList>? {
        return bankApi.loadBills(1,contentType).flatMap {
            bill -> Observable.fromArray(bill)
        }

    }
}
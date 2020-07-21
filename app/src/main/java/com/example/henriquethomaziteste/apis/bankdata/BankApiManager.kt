package com.example.henriquethomaziteste.apis.bankdata

import android.content.Context
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
import java.security.AccessControlContext
import java.util.concurrent.TimeUnit

object BankApiManager {

    val PREF_NAME = "bankUser"

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

    fun storeUserCredentials(context: Context, data: BankUserData, user: String, pass: String ){

        val sharedPrefs = context.getSharedPreferences(PREF_NAME, 0)
        val editor = sharedPrefs.edit()

        data.userId?.let { editor.putInt("userId", it) }
        editor.putString("userName", data.name)
        editor.putString("userAgency", data.agency)
        editor.putString("userAccount", data.bankAccount)
        editor.putFloat("balance", data.balance.toFloat())

        editor.putString("user", user)
        editor.putString("pass", pass)
        editor.apply()

    }

    fun getUserCredentials(context: Context): BankUserData{

        val sharedPrefs = context.getSharedPreferences(PREF_NAME, 0)
        if (sharedPrefs != null){
            return (BankUserData(
                name = sharedPrefs.getString("userName", ""),
                agency = sharedPrefs.getString("userAgency", ""),
                bankAccount = sharedPrefs.getString("userAccount", ""),
                pass = sharedPrefs.getString("pass", ""),
                user = sharedPrefs.getString("user", ""),
                balance = sharedPrefs.getFloat("balance", 0.0F).toDouble()
            ))
        }
        else{
            return BankUserData()
        }
    }

    fun clearUserCredentials (context: Context){
        val sharedPrefs = context.getSharedPreferences(PREF_NAME, 0)
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
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
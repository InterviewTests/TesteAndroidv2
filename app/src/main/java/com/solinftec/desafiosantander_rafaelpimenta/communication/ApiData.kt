package com.solinftec.desafiosantander_rafaelpimenta.communication

import com.solinftec.desafiosantander_rafaelpimenta.communication.ApiService.Companion.url
import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiData {

    @GET("statements/1")
    fun getStatements(): Call<Map<String, Any>>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body loginData: Map<String, String>): Call<LoginResponse>

    companion object {
        operator fun invoke(): ApiData {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiData::class.java)
        }
    }
}
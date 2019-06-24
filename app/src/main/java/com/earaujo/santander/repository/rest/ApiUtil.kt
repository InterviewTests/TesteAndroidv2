package com.earaujo.santander.repository.rest

import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginResponse
import com.earaujo.santander.repository.models.StatementsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiUtil {

    fun login(userName: String, password: String, callback: LoginCallback) {
        callback.onResponse(Resource.loading(null))
        getClient().create(ApiInterface::class.java).login(userName, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.body() != null) {
                    if (response.body()!!.userAccountModel != null) {
                        callback.onResponse(Resource.success(response.body()!!))
                    } else {
                        callback.onResponse(Resource.error(response.body()!!.error!!.message, null))
                    }
                } else {
                    //TODO Move strings to resources
                    callback.onResponse(Resource.error("Inválid request", null))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //TODO Move strings to resources
                callback.onResponse(Resource.error("No internet connection", null))
            }
        })
    }

    fun statements(callback: StatementsCallback) {
        callback.onResponse(Resource.loading(null))
        getClient().create(ApiInterface::class.java).statements().enqueue(object : Callback<StatementsResponse> {
            override fun onResponse(call: Call<StatementsResponse>, response: Response<StatementsResponse>) {
                if (response.body() != null) {
                    if (response.body()!!.statementList != null) {
                        callback.onResponse(Resource.success(response.body()!!))
                    } else {
                        callback.onResponse(Resource.error(response.body()!!.error!!.message, null))
                    }
                } else {
                    //TODO Move strings to resources
                    callback.onResponse(Resource.error("Inválid request", null))
                }
            }

            override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {
                //TODO Move strings to resources
                callback.onResponse(Resource.error("No internet connection", null))
            }
        })
    }

    interface LoginCallback {
        fun onResponse(loginResponse: Resource<LoginResponse>)
    }

    interface StatementsCallback {
        fun onResponse(statementsResponse: Resource<StatementsResponse>)
    }

    companion object {
        private var retrofit: Retrofit? = null

        private val ENDPOINT = "https://bank-app-test.herokuapp.com/api/"

        fun getClient(): Retrofit {
            if (retrofit != null) return retrofit as Retrofit

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit as Retrofit
        }
    }

}
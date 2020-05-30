package com.example.data.api.base

import com.example.data.util.Constantes
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClienteApi {
    private val TIMEOUT_SECONDS: Long = 300
    private var retrofit: Retrofit? = null
    private var retrofitToken: Retrofit? = null


    private val client: Retrofit
        get() {
            if (retrofit == null) {
                val httpClient = OkHttpClient.Builder()
                httpClient.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                httpClient.callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

                httpClient.addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {

                        val request = chain.request().newBuilder().build()
                        val response = chain.proceed(request)

                        return response


                    }
                })

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)



                retrofit = Retrofit.Builder()
                    .baseUrl(Constantes.ParametrosApi.DEV_HOST)
                    .addConverterFactory(GsonConverterFactory.create(GsonUtil.gsonDefault))
                    .client(httpClient.build())
                    .build()
            }
            return retrofit as Retrofit
        }

    fun <S> createService(serviceClass: Class<S>): S {
        return client.create(serviceClass)
    }
}
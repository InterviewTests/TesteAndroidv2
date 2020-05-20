package br.com.crmm.bankapplication.framework.datasource.remote.util

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class HttpClient {

    fun getHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .callTimeout(1, TimeUnit.MINUTES)
        .addInterceptor {chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build()
            )
        }
        .build()
    }
}
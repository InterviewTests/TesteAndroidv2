package br.com.vinicius.bankapp.infra

import br.com.vinicius.bankapp.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val URL_API = "https://bank-app-test.herokuapp.com/api/"

    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.MINUTES)
        .readTimeout(3, TimeUnit.MINUTES)
        .writeTimeout(3, TimeUnit.MINUTES)
        .addInterceptor(interceptor)
        .build()

    val instance: Api by lazy {

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(URL_API)
            .build()

        retrofit.create(Api::class.java)
    }
}
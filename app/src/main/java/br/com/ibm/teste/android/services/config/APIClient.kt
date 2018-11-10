package br.com.ibm.teste.android.services.config

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 16:31
 */
open class APIClient(@NotNull private val baseUrl: String) {

    private var retrofit: Retrofit

    private val checkConnectionInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build()
        chain.proceed(request)
    }

    private var okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(this.checkConnectionInterceptor)
            .build()

    init {
        retrofit = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
    }

    fun getRetrofit() = retrofit
}
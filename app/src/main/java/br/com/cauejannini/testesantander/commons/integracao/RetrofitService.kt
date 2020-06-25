package br.com.cauejannini.testesantander.commons.integracao

import android.content.Context
import br.com.cauejannini.testesantander.commons.integracao.NetworkUtils
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitService() {

    val baseUrl = "https://bank-app-test.herokuapp.com/api/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildClient())
        .build()

    fun <T> createService(serviceClass: Class<T>): T {

        return retrofit.create(serviceClass)
    }

    fun buildClient() : OkHttpClient {

//        val connectionCheckerInterface = Interceptor { chain ->
//
//            if (!NetworkUtils.isConnected(context)) {
//               throw IOException("Você parece estar offline")
//            }
//            chain.proceed(chain.request().newBuilder().build())
//
//        }

//        val authInterceptor = Interceptor { chain ->
//
//            val apiKey = BuildConfig.API_KEY
//
//            var request = chain.request()
//            val url: HttpUrl = request.url().newBuilder()
//                .addQueryParameter("access_key", apiKey)
//                .build()
//            request = request.newBuilder().url(url).build()
//
//            chain.proceed(request)
//
//        }

        return OkHttpClient.Builder()
//            .addInterceptor(connectionCheckerInterface)
//            .addInterceptor(authInterceptor)
            .build()
    }

}
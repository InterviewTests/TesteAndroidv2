package  com.santander.data.source.remote

import com.google.gson.GsonBuilder
import com.santander.data.BuildConfig
import com.santander.data.util.network.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class NetworkProvider(private val connectivityManager: ConnectivityManager) {

    private fun getRetrofit(baseUrl: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(createGsonConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttpClient())
            .build()
    }


    fun <T> createService(serviceClass: Class<T>, baseUrl: String): T {
        return getRetrofit(baseUrl).create(serviceClass)
    }


    private fun getOkHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            addInterceptor(CheckConnectivityInterceptor(connectivityManager))

        }
        return httpClient.build()
    }


    private fun createGsonConverterFactory(): Converter.Factory {
        val build = GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .create()
        return GsonConverterFactory.create(build)
    }


    private class CheckConnectivityInterceptor(val connectivityManager: ConnectivityManager) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {

            if (!connectivityManager.isConnected()) {
                throw IOException("no connected")
            }

            return chain.proceed(chain.request())
        }
    }

}
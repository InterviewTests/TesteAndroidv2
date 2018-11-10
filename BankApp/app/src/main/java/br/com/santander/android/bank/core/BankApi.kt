package br.com.santander.android.bank.core

import br.com.santander.android.bank.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class BankApi {

    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd"

        fun retrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(datePattern))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }

        private val client : OkHttpClient = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                this.addInterceptor(interceptor)
            }
        }.build()

        private val datePattern = GsonBuilder()
            .setDateFormat(DATE_PATTERN)
            .create()
    }
}
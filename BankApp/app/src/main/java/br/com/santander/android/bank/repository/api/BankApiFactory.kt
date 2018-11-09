package br.com.santander.android.bank.repository.api

import br.com.santander.android.bank.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class BankApiFactory {

    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd"

        fun create(): BankApiService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(datePattern))
                .client(client)
                .build()
                .create(BankApiService::class.java)
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
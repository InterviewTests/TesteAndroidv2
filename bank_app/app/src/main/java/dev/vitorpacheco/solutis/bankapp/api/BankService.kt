package dev.vitorpacheco.solutis.bankapp.api

import com.google.gson.GsonBuilder
import dev.vitorpacheco.solutis.bankapp.BankApp
import dev.vitorpacheco.solutis.bankapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BankService {

    companion object {
        fun createService(): BankApi {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            logging.redactHeader("Authorization")
            logging.redactHeader("Cookie")

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val gson = GsonBuilder()
                .create()

            val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BankApp.API_BASE_URL)
                .build()

            return retrofit.create(BankApi::class.java)
        }
    }

}
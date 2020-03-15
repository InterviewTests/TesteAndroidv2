package dev.vitorpacheco.solutis.bankapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BankService {

    companion object {
        fun create(): BankApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            logging.redactHeader("Authorization")
            logging.redactHeader("Cookie")

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .build()

            return retrofit.create(BankApi::class.java)
        }
    }

}
package com.develop.zupp_bank.di

import com.develop.zupp_bank.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class ModuleHttp {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        builder.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return builder.build()
    }

    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .registerTypeAdapter(Date::class.java, JsonDeserializer { p1, _, _ ->
                Date(p1.asLong)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { date, _, _ ->
                JsonPrimitive(date.time)
            })
            .create()
    }

    @Provides
    @Named("baseurl")
    internal fun provideBaseUrl(): String {
        return BuildConfig.URL
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(@Named("baseurl") baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}
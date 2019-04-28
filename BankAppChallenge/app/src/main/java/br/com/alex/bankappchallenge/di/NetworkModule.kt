package br.com.alex.bankappchallenge.di

import br.com.alex.bankappchallenge.BuildConfig
import br.com.alex.bankappchallenge.config.BuildConfigName
import br.com.alex.bankappchallenge.network.BankAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val PROPERTY_BASE_URL = "baseUrl"

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            val debugVariants = arrayOf(BuildConfigName.DEBUG)
            level =
                if (debugVariants.contains(BuildConfig.BUILD_TYPE)) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        } as Interceptor
    }

    single {
        OkHttpClient
            .Builder()
            .addInterceptor(get())
            .build()
    }

    single {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(get())
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(BankAPI::class.java)
    }
}
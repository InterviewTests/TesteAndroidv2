package br.com.mdr.testeandroid.di

import br.com.mdr.testeandroid.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Marlon D. Rocha
 * @since 04/07/2020
 */

val networkModule = module {

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    // OkHttp Client
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get())
            .build()
    }

    // Http Logging Interceptor
    single {
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger {}
        ).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Interceptor
    single {
        Interceptor { chain ->
            chain.request().run {
                newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-type", "application/json")
                    .method(this.method(), this.body())
                    .build()
                    .let(chain::proceed)
            }
        }
    }

    // Gson
    single {
        GsonBuilder().create()
    }

    // GsonConverterFactory
    single {
        GsonConverterFactory.create(get()) as Converter.Factory
    }
}
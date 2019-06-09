package com.zuptest.santander.di

import android.content.Context
import android.content.SharedPreferences
import com.zuptest.santander.BuildConfig
import com.zuptest.santander.BuildConfig.DEBUG
import com.zuptest.santander.data.remote.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    val module = module {

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
        }

        single<Api> {
            (get() as Retrofit).create(Api::class.java)
        }

        single<OkHttpClient> {
            val interceptor = HttpLoggingInterceptor().apply {
                level = getLoggingLevelByBuildType()
            }
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        }

        single<SharedPreferences> {
            androidApplication().getSharedPreferences("zup_preferences", Context.MODE_PRIVATE);
        }
    }

    private fun getLoggingLevelByBuildType(): HttpLoggingInterceptor.Level {
        return if (DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}
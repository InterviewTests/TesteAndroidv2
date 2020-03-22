package com.example.ibm_test.di.module

import android.app.Application
import com.example.ibm_test.service.CacheInterceptor
import com.example.ibm_test.service.IBMNetwork
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class NetworkModule{

    companion object {
        private const val CACHE_LIMIT = (1024 * 1024 * 5).toLong() //5MB
    }

    @Provides
    @Singleton
    @Named("CachedOkHttpClient")
    fun providesCachedOkHttpClient(application: Application): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .cache(Cache(application.cacheDir, CACHE_LIMIT))
            .addInterceptor(CacheInterceptor())
            .build()
    }

    @Provides
    @Named("IBMApi")
    fun provideZapGroupAPI(gson: Gson, @Named("CachedOkHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://bank-app-test.herokuapp.com/api/")
            .build()
    }

    @Provides
    fun provideZapGroupService(@Named("IBMApi") IBMApi: Retrofit): IBMNetwork{
        return IBMApi.create(IBMNetwork::class.java)
    }
}
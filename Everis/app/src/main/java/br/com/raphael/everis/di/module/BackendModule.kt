package br.com.raphael.everis.di.module

import br.com.raphael.everis.BuildConfig
import br.com.raphael.everis.remote.BackendService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class BackendModule {

    open val baseUrl by lazy { BuildConfig.BASE_URL }

    @Provides
    @Singleton
    @Named("backendRetrofit")
    fun providesRetrofitBackend(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    @Provides
    @Singleton
    open fun providesBackendService(@Named("backendRetrofit") retrofit: Retrofit): BackendService =
        retrofit.create(BackendService::class.java)

}
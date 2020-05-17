package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.LoginService
import br.com.crmm.bankapplication.framework.datasource.remote.util.HttpClient
import br.com.crmm.bankapplication.framework.datasource.remote.util.NetworkServiceProvider
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideLoginService(get()) }
    single { provideRetrofit(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return HttpClient().getHttpClient()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return NetworkServiceProvider().getService(okHttpClient)
}

fun provideLoginService(retrofit: Retrofit): LoginService{
    return retrofit.create(LoginService::class.java)
}
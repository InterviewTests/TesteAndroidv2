package br.com.crmm.bankapplication.di.module

import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.LoginService
import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.StatementService
import br.com.crmm.bankapplication.framework.datasource.remote.util.HttpClient
import br.com.crmm.bankapplication.framework.datasource.remote.util.NetworkServiceProvider
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideLoginService(get()) }
    factory { provideStatementService(get()) }
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

fun provideStatementService(retrofit: Retrofit): StatementService{
    return retrofit.create(StatementService::class.java)
}
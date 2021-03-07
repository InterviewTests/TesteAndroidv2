package br.com.silas.data.remote.api

import br.com.silas.data.BuildConfig
import br.com.silas.data.remote.login.LoginResponse
import br.com.silas.data.remote.statements.StatementsResponse
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TesteAndroidv2ServiceImpl : ServiceTesteAndroidV2 {
    companion object {
        private const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
        private const val TIMEOUT = 10L
    }
    private var testeAndroidV2Service: ServiceTesteAndroidV2

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) BODY else BASIC

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        httpClient.addInterceptor { chain ->
            val requestOrigin = chain.request()
            val request = requestOrigin.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .method(requestOrigin.method(), requestOrigin.body())
                .build()
            chain.proceed(request)
        }

        val client = httpClient.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
            .client(client)
            .build()

        testeAndroidV2Service = retrofit.create(ServiceTesteAndroidV2::class.java)

    }

    override fun fetchUser(login: String, password: String): Single<LoginResponse> {
        return testeAndroidV2Service.fetchUser(login, password)
    }

    override fun fetchStatements(userId: Int): Single<StatementsResponse> {
        return testeAndroidV2Service.fetchStatements(userId)
    }
}
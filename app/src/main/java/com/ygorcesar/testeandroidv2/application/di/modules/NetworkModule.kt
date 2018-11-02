package com.ygorcesar.testeandroidv2.application.di.modules

import com.squareup.moshi.Moshi
import com.ygorcesar.testeandroidv2.BuildConfig
import com.ygorcesar.testeandroidv2.application.di.scopes.ApplicationScope
import com.ygorcesar.testeandroidv2.base.common.exception.HttpError
import com.ygorcesar.testeandroidv2.base.common.exception.ServerError
import com.ygorcesar.testeandroidv2.base.common.exception.UnauthorizedError
import com.ygorcesar.testeandroidv2.base.common.network.BaseResponse
import com.ygorcesar.testeandroidv2.base.data.preferences.PreferencesHelper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val CONTENT_TYPE_PROPERTY = "content-type"
private const val FORM_URL_ENCODED_VALUE = "application/x-www-form-urlencoded"

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        httpClient: OkHttpClient,
        baseUrl: String,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converter)
            .client(httpClient)
            .build()

    }

    @Provides
    @ApplicationScope
    fun provideNetworkTimeout(): Long = 120L

    @Provides
    @ApplicationScope
    fun provideBaseUrl(): String = BuildConfig.API_URL

    @Provides
    @ApplicationScope
    fun provideConverter(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @ApplicationScope
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message); })
    }

    @Provides
    @ApplicationScope
    fun provideClient(
        networkTimeoutSecond: Long,
        logger: HttpLoggingInterceptor,
        auth: AuthenticationInterceptor
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.readTimeout(networkTimeoutSecond, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(networkTimeoutSecond, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logger)
        }

        okHttpClientBuilder.addInterceptor(auth)

        return okHttpClientBuilder.build()
    }

    /**
     * Custom interceptor to add the Auth Token in every request when the
     * <b>No-Authentication</b> header parameter is missing
     */
    @Singleton
    class AuthenticationInterceptor @Inject constructor(
        private val moshi: Moshi,
        private val preferencesHelper: PreferencesHelper
    ) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val builder = request.newBuilder()

            request = builder.build()

            val response = chain.proceed(request)

            when (response.code()) {
                in 200..206 -> {
                    val body = response.body()?.string()
                    catchServerValidations(body)
                }
                400 -> { // Validations from backend
                    val body = response.body()?.string()
                    catchServerValidations(body)
                }
                401 -> { // Unauthorized
                    preferencesHelper.logout()
                    throw UnauthorizedError
                }
                else -> { // Something that we are not know
                    Timber.e("RESPONSE_CODE:${response.code()}; BODY: ${response.body()?.string().toString()} ")
                    throw HttpError
                }
            }

            return response
        }

        private fun catchServerValidations(body: String?) {
            if (body != null) {
                Timber.d(body)
                val response = moshi
                    .adapter(BaseResponse::class.java)
                    .fromJson(body)
                response?.error?.let { throw ServerError(it) }
            }
            throw HttpError
        }
    }
}
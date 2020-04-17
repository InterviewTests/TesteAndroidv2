package br.com.rcp.bank.injection.modules

import br.com.rcp.bank.ServiceAPI
import br.com.rcp.bank.annotations.Network
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {
	@Provides
	@Network
	fun provideOKHTTPClient(): OkHttpClient {
		val		builder		= OkHttpClient.Builder()
		builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
		builder.connectTimeout(15, TimeUnit.SECONDS)
		builder.writeTimeout(60, TimeUnit.SECONDS)
		builder.readTimeout(60, TimeUnit.SECONDS)
		builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
		return builder.build()
	}

	@Provides
	@Network
	fun provideAPI(retrofit: Retrofit): ServiceAPI {
		return retrofit.create(ServiceAPI::class.java)
	}

	@Provides
	@Network
	fun provideRetrofit(client: OkHttpClient): Retrofit {
		return Retrofit.Builder()
				.baseUrl("https://bank-app-test.herokuapp.com/api/")
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(CoroutineCallAdapterFactory())
				.build()
	}
}
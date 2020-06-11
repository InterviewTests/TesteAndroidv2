package projects.kevin.bankapp.user.service

import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import projects.kevin.bankapp.user.detail.DetailApiResponse
import projects.kevin.bankapp.user.login.LoginApiRequest
import projects.kevin.bankapp.user.login.LoginApiResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiUserService {

    companion object {
        const val API_URL = "https://bank-app-test.herokuapp.com/api/"
        operator fun invoke(): ApiUserService{
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiUserService::class.java)
        }
    }

    @POST("login")
    fun login(@Body configs: LoginApiRequest): Single<LoginApiResponse>

    @GET("statements/{userId}")
    fun fetchUserDetail(@Path("userId") userId: Long): Single<DetailApiResponse>
}
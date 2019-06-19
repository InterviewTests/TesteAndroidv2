package br.com.vinicius.bankapp.data.remote

import br.com.vinicius.bankapp.data.remote.model.LoginModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService{

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") username: String,
        @Field("password") password: String
    ): Call<LoginModel>

    companion object {
        operator fun invoke () : ApiService {
            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .build()
                .create(ApiService::class.java)
        }
    }
}
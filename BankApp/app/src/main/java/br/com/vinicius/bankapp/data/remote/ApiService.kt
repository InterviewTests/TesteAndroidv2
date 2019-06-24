package br.com.vinicius.bankapp.data.remote

import br.com.vinicius.bankapp.data.remote.model.LoginResponse
import br.com.vinicius.bankapp.data.remote.model.StatementResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService{

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("statements/{id}")
    fun getStatements(
        @Path("id") id:Int
    ): Call<StatementResponse>

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
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .build()
                .create(ApiService::class.java)
        }
    }
}
package br.com.silas.data.remote.api

import br.com.silas.data.remote.login.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface TesteAndroidV2Service {
//    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("login")
    fun fetchUser(
        @Field("user") login: String,
        @Field("password") password: String
    ): Single<LoginResponse>
}
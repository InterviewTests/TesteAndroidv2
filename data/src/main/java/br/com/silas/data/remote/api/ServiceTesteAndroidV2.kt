package br.com.silas.data.remote.api

import br.com.silas.data.remote.login.LoginResponse
import br.com.silas.data.remote.statements.StatementsResponse
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface ServiceTesteAndroidV2 {

    @FormUrlEncoded
    @POST("login")
    fun fetchUser(
        @Field("user") login: String,
        @Field("password") password: String
    ): Single<LoginResponse>

    @GET("statements/{userId}")
    fun fetchStatements(
        @Path("userId") userId: Int,
    ): Single<StatementsResponse>
}
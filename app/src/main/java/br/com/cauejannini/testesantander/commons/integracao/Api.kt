package br.com.cauejannini.testesantander.commons.integracao

import br.com.cauejannini.testesantander.login.LoginResponseModel
import br.com.cauejannini.testesantander.statements.StatementsResponseModel
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") password: String): Call<LoginResponseModel>

    @GET("statements/{userId}")
    fun getStatements(@Path("userId") userId: Int): Call<StatementsResponseModel>
}
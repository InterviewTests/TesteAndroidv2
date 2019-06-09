package com.br.projetotestesantanter.api

import com.br.projetotestesantanter.refactor.loginScreen.LoginModel
import com.br.projetotestesantanter.refactor.statementScreen.StatementModel
import retrofit2.Call
import retrofit2.http.*


interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") password : String ) : Call<LoginModel.Login>


    @GET("statements/{idUser}")
    fun getStatements(@Path("idUser") idUser : Long) : Call<StatementModel.ListStatemt>
}
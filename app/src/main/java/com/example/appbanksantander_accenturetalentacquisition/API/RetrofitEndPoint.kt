package com.example.appbanksantander_accenturetalentacquisition.API


import com.example.appbanksantander_accenturetalentacquisition.Model.StatementResponse
import com.example.appbanksantander_accenturetalentacquisition.Model.UserAccountModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitEndPoint {

    @POST ("api/login")
    fun loginUser(): Call<UserAccountModel>

    @GET("api/statements/1")
    fun getStatements(@Query("idUser") idUser: Int?): Call<StatementResponse>
}
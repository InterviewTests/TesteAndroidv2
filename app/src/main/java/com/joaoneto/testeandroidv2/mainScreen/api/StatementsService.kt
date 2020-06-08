package com.joaoneto.testeandroidv2.mainScreen.api

import com.joaoneto.testeandroidv2.mainScreen.model.StatementsResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface StatementsService {
    @GET("statements/1")
    fun getStatements(): Call<StatementsResponseModel>
}

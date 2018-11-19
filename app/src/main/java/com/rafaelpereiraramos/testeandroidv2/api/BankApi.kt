package com.rafaelpereiraramos.testeandroidv2.api

import androidx.lifecycle.LiveData
import com.rafaelpereiraramos.testeandroidv2.db.model.UserTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
interface BankApi {

    @FormUrlEncoded
    @POST("login/")
    fun login(
        @Field("userName")userName: String,
        @Field("password")password: String
    ): ApiResponseLiveData<UserTO>
}
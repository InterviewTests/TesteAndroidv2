package com.br.natanfelipe.bankapp.api

import com.br.natanfelipe.bankapp.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface BankApi {

    @FormUrlEncoded
    @POST("login")
    fun doLogin(@Header("Content-Type") contentType:String,
                @Field("user") user: String,
                @Field("password") password:String) :  Observable<Response>

    @GET("statements/{id}")
    fun loadBills(@Path("id") id:Int,
                  @Header("Content-Type") contentType:String) : Observable<StatementList>
}
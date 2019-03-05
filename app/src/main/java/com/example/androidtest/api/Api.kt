package com.example.androidtest.api

import com.example.androidtest.repository.AccountRaw
import com.example.androidtest.repository.StatementRaw
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.*

const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("user") user: String,
        @Field("password") pass: String
    ): Observable<PostLoginResponse>

    @GET("statements/{user_id}")
    fun getStatements(
        @Path("user_id") userId: Int
    ): Observable<GetStatementsResponse>
}

data class ApiError(@SerializedName("message") val message: String?)

data class PostLoginResponse(
    @SerializedName("userAccount") val userAccount: AccountRaw,
    @SerializedName("error") val error: ApiError
)
data class GetStatementsResponse(
    @SerializedName("statementList") val statementList: ArrayList<StatementRaw>,
    @SerializedName("error") val error: ApiError
)


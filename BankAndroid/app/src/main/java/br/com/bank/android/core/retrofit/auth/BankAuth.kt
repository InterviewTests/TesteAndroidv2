package br.com.bank.android.core.retrofit.auth

import br.com.bank.android.core.retrofit.auth.response.UserAccountBodyResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BankAuth {

    @FormUrlEncoded
    @POST("login")
    suspend fun onLogin(@Field("user") user: String, @Field("password") password: String): Response<UserAccountBodyResponse>
}
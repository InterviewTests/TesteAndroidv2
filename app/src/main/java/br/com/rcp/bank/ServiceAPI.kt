package br.com.rcp.bank

import br.com.rcp.bank.dto.LoginResponseDTO
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServiceAPI {
    @FormUrlEncoded
    @POST("login")
    suspend fun doLogin(@Field("user") username: String, @Field("password") password: String): Response<LoginResponseDTO>
}
package br.com.rcp.bank

import br.com.rcp.bank.dto.LoginResponseDTO
import br.com.rcp.bank.dto.StatementListDTO
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {
    @FormUrlEncoded
    @POST("login")
    suspend fun doLogin(@Field("user") username: String, @Field("password") password: String): Response<LoginResponseDTO>

    @GET("https://bank-app-test.herokuapp.com/api/statements/{path}")
    suspend fun requestStatements(@Path("path") identifier: Long): Response<StatementListDTO>
}
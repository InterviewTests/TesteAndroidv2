package fingerfire.com.extractbank.api

import fingerfire.com.extractbank.features.statements.data.StatementsResponse
import fingerfire.com.extractbank.model.Login
import fingerfire.com.extractbank.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankApi {
    @POST("api/v1/login")
    suspend fun login(
        @Body login: Login
    ): Response<User>

    @GET("api/v1/login/{loginId}/statement")
    suspend fun getStatement(
        @Path("loginId") id: String
    ): Response<List<StatementsResponse>>
}
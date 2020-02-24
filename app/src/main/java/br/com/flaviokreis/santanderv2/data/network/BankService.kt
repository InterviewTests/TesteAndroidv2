package br.com.flaviokreis.santanderv2.data.network

import androidx.lifecycle.LiveData
import br.com.flaviokreis.santanderv2.data.network.util.ApiResponse
import br.com.flaviokreis.santanderv2.data.response.LoginResponse
import br.com.flaviokreis.santanderv2.data.response.StatementsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") user: String,
        @Field("password") password: String
    ): LiveData<ApiResponse<LoginResponse>>

    @GET("statements/{userId}")
    fun statements(
        @Path("userId") userId: Int
    ): LiveData<ApiResponse<StatementsResponse>>
}
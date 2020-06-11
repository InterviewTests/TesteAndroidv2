package projects.kevin.bankapp.user.service

import io.reactivex.Single
import projects.kevin.bankapp.user.detail.DetailApiResponse
import projects.kevin.bankapp.user.login.LoginApiRequest
import projects.kevin.bankapp.user.login.LoginApiResponse
import retrofit2.http.*

interface ApiUserService {

    @POST("login")
    fun login(@Body configs: LoginApiRequest): Single<LoginApiResponse>

    @GET("statements/{userId}")
    fun fetchUserDetail(@Path("userId") userId: Long): Single<DetailApiResponse>
}
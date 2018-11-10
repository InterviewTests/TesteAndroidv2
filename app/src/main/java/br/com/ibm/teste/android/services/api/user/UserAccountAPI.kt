package br.com.ibm.teste.android.services.api.user

import br.com.ibm.teste.android.services.models.UserRequest
import br.com.ibm.teste.android.services.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:27
 */
interface UserAccountAPI {

    /**
     * The API for login in your bank account
     */
    @POST("login")
    fun login(@Body user: UserRequest): Call<UserResponse>

}
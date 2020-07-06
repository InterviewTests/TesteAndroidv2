package br.com.mdr.testeandroid.api

import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */
interface SignInApi {
    companion object {
        private const val SIGN_IN_PATH = "login"
    }

    @POST(SIGN_IN_PATH)
    suspend fun signInUser(@Body user: SignInApiModel): Response<UserApiModel>
}
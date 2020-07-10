package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.api.SignInApi
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import com.google.gson.Gson

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */
class SignInRepository(
    private val signInApi: SignInApi
) : BaseRepository(), ISignInRepository {

    override suspend fun signInUser(user: SignInApiModel): UserApiModel? {
        return handleResponse(signInApi.signInUser(user))
    }
}
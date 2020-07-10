package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel

interface ISignInRepository {
    suspend fun signInUser(user: SignInApiModel): UserApiModel?
}
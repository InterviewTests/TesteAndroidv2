package br.com.mdr.testeandroid.repository

import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User

interface ISignInRepository {
    suspend fun signInUser(user: SignInApiModel): UserApiModel?
    fun saveLoggedUser(user: User)
    fun getLoggedUser(): User?
}
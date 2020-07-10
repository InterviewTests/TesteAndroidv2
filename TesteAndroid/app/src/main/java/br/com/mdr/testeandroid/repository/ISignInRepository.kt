package br.com.mdr.testeandroid.repository

import android.content.Context
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User

interface ISignInRepository {
    suspend fun signInUser(user: SignInApiModel): UserApiModel?
    fun saveLoggedUser(user: User, context: Context)
    fun getLoggedUser(context: Context): User?
}
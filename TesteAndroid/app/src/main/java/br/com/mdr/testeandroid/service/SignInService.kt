package br.com.mdr.testeandroid.service

import android.content.Context
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.repository.SignInRepository


class SignInService(
    private val signInRepository: SignInRepository
) : ISignInService {

    override suspend fun loginUser(signInUser: SignInApiModel): UserApiModel? {
        return signInRepository.signInUser(signInUser)
    }

    override fun saveLoggedUser(user: User, context: Context) {
        signInRepository.saveLoggedUser(user, context)
    }

    override fun getLoggedUser(context: Context): User? {
        return signInRepository.getLoggedUser(context)
    }
}
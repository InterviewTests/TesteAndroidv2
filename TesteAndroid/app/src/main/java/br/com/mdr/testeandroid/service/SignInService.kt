package br.com.mdr.testeandroid.service

import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.repository.ISignInRepository


class SignInService(
    private val signInRepository: ISignInRepository
) : ISignInService {

    override suspend fun loginUser(signInUser: SignInApiModel): UserApiModel? {
        return signInRepository.signInUser(signInUser)
    }

    override fun saveLoggedUser(user: User) {
        signInRepository.saveLoggedUser(user)
    }

    override fun getLoggedUser(): User? {
        return signInRepository.getLoggedUser()
    }
}
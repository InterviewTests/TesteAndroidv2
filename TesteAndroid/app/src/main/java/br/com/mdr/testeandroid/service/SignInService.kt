package br.com.mdr.testeandroid.service

import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.repository.SignInRepository


class SignInService(
    private val signInRepository: SignInRepository
) : ISignInService {

    override suspend fun loginUser(signInUser: SignInApiModel): UserApiModel? {
        return signInRepository.signInUser(signInUser)
    }
}
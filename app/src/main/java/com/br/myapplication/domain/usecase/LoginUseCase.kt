package com.br.myapplication.domain.usecase

import com.br.myapplication.data.model.ResponseLogin
import com.br.myapplication.data.repository.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) :
    BaseUseCase<ResponseLogin, LoginUseCase.Params>() {

    override suspend fun run(param: Params): ResponseLogin = loginRepository.doLogin(param.user, param.password)

    data class Params(val user: String, val password:String)
}
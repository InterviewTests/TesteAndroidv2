package com.br.myapplication.usecase

import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.repository.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) :
    BaseUseCase<ResponseLogin, LoginUseCase.Params>() {

    override suspend fun run(param: Params): ResponseLogin = loginRepository.doLogin(param.user, param.password)

    data class Params(val user: String, val password:String)
}
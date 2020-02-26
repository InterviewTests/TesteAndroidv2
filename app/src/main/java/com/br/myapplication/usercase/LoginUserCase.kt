package com.br.myapplication.usercase

import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.repository.LoginRepository
import com.br.myapplication.service.ApiResult

class LoginUserCase(private val loginRepository: LoginRepository) :
    BaseUserCase<ResponseLogin, LoginUserCase.Params>() {

    override suspend fun run(param: Params): ApiResult<ResponseLogin> = loginRepository.doLogin(param.user, param.password)

    data class Params(val user: String, val password:String)
}
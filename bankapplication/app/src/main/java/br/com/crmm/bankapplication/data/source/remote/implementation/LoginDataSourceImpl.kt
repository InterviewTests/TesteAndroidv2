package br.com.crmm.bankapplication.data.source.remote.implementation

import br.com.crmm.bankapplication.data.source.remote.abstraction.LoginRemoteDataSource
import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.LoginService
import br.com.crmm.bankapplication.framework.datasource.remote.common.AbstractService
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.LoginResponseDTO
import io.reactivex.rxjava3.core.Maybe
import retrofit2.HttpException

class LoginDataSourceImpl(
    private val loginService: LoginService
): AbstractService(),
    LoginRemoteDataSource {

    override fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginResponseDTO>{
        return Maybe.fromCallable {
            println("TEST - user = ${loginRequestDTO.user} / pass = ${loginRequestDTO.password}")
            val response = loginService.login(loginRequestDTO).execute()
            println("TEST - user = ${loginRequestDTO.user} / pass = ${loginRequestDTO.password} - finish")
            when {
                response.isSuccessful -> response.body()
                else -> throw HttpException(response)
            }
        }
    }

}
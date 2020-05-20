package br.com.crmm.bankapplication.data.source.remote.implementation

import br.com.crmm.bankapplication.data.source.remote.abstraction.LoginRemoteDataSource
import br.com.crmm.bankapplication.data.state.LoginDataState
import br.com.crmm.bankapplication.framework.datasource.remote.abstraction.LoginService
import br.com.crmm.bankapplication.framework.datasource.remote.common.AbstractService
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import io.reactivex.rxjava3.core.Maybe

class LoginDataSourceImpl(
    private val loginService: LoginService
): AbstractService(), LoginRemoteDataSource {

    override fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginDataState>{
        return Maybe.fromCallable {
            val response = loginService.login(loginRequestDTO).execute()
            val body = response.body()

            when{
                body?.userAccountResponseDTO != null -> {
                    LoginDataState.SuccessfullyResponseState(
                        body.userAccountResponseDTO
                    )
                }
                body?.errorResponseDTO?.code != null -> {
                    LoginDataState.UnsuccessfullyResponseState(
                        body.errorResponseDTO
                    )
                }
                else -> LoginDataState.UnmappedErrorState
            }
        }
    }

}
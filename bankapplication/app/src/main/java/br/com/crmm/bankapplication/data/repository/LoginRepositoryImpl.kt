package br.com.crmm.bankapplication.data.repository

import br.com.crmm.bankapplication.data.source.remote.abstraction.LoginRemoteDataSource
import br.com.crmm.bankapplication.data.state.LoginDataState
import br.com.crmm.bankapplication.domain.repository.LoginRepository
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import io.reactivex.rxjava3.core.Maybe

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
): LoginRepository {

    override fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginDataState> {
        return loginRemoteDataSource.performedLogin(loginRequestDTO)
    }

}
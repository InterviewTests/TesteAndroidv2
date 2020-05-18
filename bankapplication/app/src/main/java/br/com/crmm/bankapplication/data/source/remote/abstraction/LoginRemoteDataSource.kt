package br.com.crmm.bankapplication.data.source.remote.abstraction

import br.com.crmm.bankapplication.data.source.remote.state.LoginRemoteState
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import io.reactivex.rxjava3.core.Maybe

interface LoginRemoteDataSource {

    fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginRemoteState>

}
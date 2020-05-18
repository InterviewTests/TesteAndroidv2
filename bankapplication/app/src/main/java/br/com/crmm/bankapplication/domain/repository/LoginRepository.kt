package br.com.crmm.bankapplication.domain.repository

import br.com.crmm.bankapplication.data.source.remote.state.LoginRemoteState
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import io.reactivex.rxjava3.core.Maybe

interface LoginRepository {

    fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginRemoteState>

}
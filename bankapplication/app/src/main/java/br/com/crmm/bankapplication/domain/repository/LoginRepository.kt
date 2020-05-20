package br.com.crmm.bankapplication.domain.repository

import br.com.crmm.bankapplication.data.state.LoginDataState
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import io.reactivex.rxjava3.core.Maybe

interface LoginRepository {

    fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginDataState>

}
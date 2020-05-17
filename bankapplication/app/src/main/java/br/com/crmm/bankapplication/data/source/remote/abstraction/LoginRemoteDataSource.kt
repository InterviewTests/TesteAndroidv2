package br.com.crmm.bankapplication.data.source.remote.abstraction

import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.LoginResponseDTO
import io.reactivex.rxjava3.core.Maybe

interface LoginRemoteDataSource {

    fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginResponseDTO>

}
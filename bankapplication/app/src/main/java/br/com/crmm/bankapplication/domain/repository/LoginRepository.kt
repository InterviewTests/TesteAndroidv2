package br.com.crmm.bankapplication.domain.repository

import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.LoginResponseDTO
import io.reactivex.rxjava3.core.Maybe

interface LoginRepository {

    fun performedLogin(loginRequestDTO: LoginRequestDTO): Maybe<LoginResponseDTO>

}
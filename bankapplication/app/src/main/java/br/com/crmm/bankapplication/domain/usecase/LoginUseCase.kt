package br.com.crmm.bankapplication.domain.usecase

import br.com.crmm.bankapplication.data.source.remote.dto.request.LoginRequestDTO

class LoginUseCase {

    fun execute(loginRequestDTO: LoginRequestDTO) = println("TEST - user = ${loginRequestDTO.user} / pass = ${loginRequestDTO.password}")

}
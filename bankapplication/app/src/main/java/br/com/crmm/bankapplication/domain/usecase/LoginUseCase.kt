package br.com.crmm.bankapplication.domain.usecase

import br.com.crmm.bankapplication.domain.repository.LoginRepository
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO

class LoginUseCase(
    private val loginRepository: LoginRepository
) {

    fun execute(loginRequestDTO: LoginRequestDTO) = loginRepository.performedLogin(loginRequestDTO)
}
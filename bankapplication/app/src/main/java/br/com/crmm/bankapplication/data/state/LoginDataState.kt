package br.com.crmm.bankapplication.data.state

import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.ErrorResponseDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.UserAccountResponseDTO

sealed class LoginDataState {

    data class SuccessfullyResponseState(
        val userAccountResponseDTO: UserAccountResponseDTO
    ): LoginDataState()

    data class UnsuccessfullyResponseState(
        val errorResponseDTO: ErrorResponseDTO
    ): LoginDataState()

    object UnmappedErrorState : LoginDataState()
}
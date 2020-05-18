package br.com.crmm.bankapplication.data.source.remote.state

import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.ErrorDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.UserAccountDTO

sealed class LoginRemoteState {

    data class SuccessfullyResponseState(
        val userAccountDTO: UserAccountDTO
    ): LoginRemoteState()

    data class UnsuccessfullyResponseState(
        val error: ErrorDTO
    ): LoginRemoteState()

    object UnmappedErrorState : LoginRemoteState()
}
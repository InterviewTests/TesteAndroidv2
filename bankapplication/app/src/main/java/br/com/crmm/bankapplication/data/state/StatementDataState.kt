package br.com.crmm.bankapplication.data.state

import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.ErrorResponseDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.StatementDataResponseDTO

sealed class StatementDataState {

    data class SuccessfullyResponseState(
        val statementDataResponseDTO: List<StatementDataResponseDTO>?
    ): StatementDataState()

    data class UnsuccessfullyResponseState(
        val errorResponseDTO: ErrorResponseDTO
    ): StatementDataState()

    object UnmappedErrorState : StatementDataState()
}
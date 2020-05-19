package br.com.crmm.bankapplication.framework.presentation.ui.statement.state

import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.ErrorResponseDTO
import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.StatementDataResponseDTO

sealed class StatementViewState {

    data class SuccessfullyLoadState(
        val statementDataResponseDTO: List<StatementDataResponseDTO>?
    ): StatementViewState()

    data class UnsuccessfullyLoadState(
        val errorResponseDTO: ErrorResponseDTO
    ): StatementViewState()

    object UnmappedErrorState : StatementViewState()

    object LogoutState: StatementViewState()
}
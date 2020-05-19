package br.com.crmm.bankapplication.framework.presentation.ui.login.state

import br.com.crmm.bankapplication.framework.datasource.remote.dto.response.UserAccountResponseDTO

sealed class LoginViewState {
    object InvalidInputUsernameViewState: LoginViewState()
    object InvalidInputPasswordViewState: LoginViewState()
    object LoadingViewState: LoginViewState()
    object UnmappedErrorState: LoginViewState()
    object NoneState: LoginViewState()
    data class SuccessfullyLoginState(
        val userAccountResponseDTO: UserAccountResponseDTO
    ): LoginViewState()
    data class UnsuccessfullyLoginState(
        val code: Int,
        val msg: String
    ): LoginViewState()
}
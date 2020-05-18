package br.com.crmm.bankapplication.framework.presentation.ui.login.state

sealed class LoginViewState {
    object InvalidInputUsernameViewState: LoginViewState()
    object InvalidInputPasswordViewState: LoginViewState()
    object LoadingViewState: LoginViewState()
    object UnmappedErrorState: LoginViewState()
    object SuccessfullyLoginState: LoginViewState()
    data class UnsuccessfullyLoginState(
        val code: Int,
        val msg: String
    ): LoginViewState()
}
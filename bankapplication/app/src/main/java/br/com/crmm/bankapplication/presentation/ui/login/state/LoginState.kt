package br.com.crmm.bankapplication.presentation.ui.login.state

sealed class LoginState {
    object InvalidInputUsernameState : LoginState()
    object InvalidInputPasswordState : LoginState()
    object LoadingState : LoginState()
}
package br.com.crmm.bankapplication.framework.presentation.ui.login.state

sealed class LoginState {
    object InvalidInputUsernameState : LoginState()
    object InvalidInputPasswordState : LoginState()
    object LoadingState : LoginState()
}
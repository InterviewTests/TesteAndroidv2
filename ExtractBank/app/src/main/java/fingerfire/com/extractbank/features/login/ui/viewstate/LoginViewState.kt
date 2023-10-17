package fingerfire.com.extractbank.features.login.ui.viewstate

import fingerfire.com.extractbank.model.User

sealed class LoginViewState {
    data class Success(val user: User) : LoginViewState()
    data class Error(val message: String) : LoginViewState()
}
package br.com.alex.bankappchallenge.feature.login

sealed class LoginStates {
    object Loading : LoginStates()
    data class Error(val errorMessage: String) : LoginStates()
    object PasswordInvalid : LoginStates()
    object UserInvalid : LoginStates()
    object EmptyUser : LoginStates()
    object EmptyPassword : LoginStates()
    data class HasUser(val userLogin: String) : LoginStates()
}

data class LoginState(
    val isLoading: Boolean = false,
    val isLoadError: Boolean = false,
    val errorMessage: String = "",
    val userLogin: String = "",
    val isPasswordInvalid: Boolean = false,
    val isUserInvalid: Boolean = false,
    val isUserEmpty: Boolean = false,
    val isPasswordEmpty: Boolean = false
)
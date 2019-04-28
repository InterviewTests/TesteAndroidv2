package br.com.alex.bankappchallenge.feature.login

sealed class LoginStates {
    object Loading : LoginStates()
    data class Error(val errorMessage: String) : LoginStates()
    object PasswordInvalid : LoginStates()
    object EmptyFields : LoginStates()
    data class HasUser(val userLogin: String) : LoginStates()
}

data class LoginState(
    val isLoading: Boolean = false,
    val isLoadError: Boolean = false,
    val errorMessage: String = "",
    val userLogin: String = ""
)
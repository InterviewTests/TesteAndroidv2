package br.com.alex.bankappchallenge.feature.login

class LoginReducer : LoginReducerContract {

    override fun reducer(
        loginState: LoginState?,
        loginStates: LoginStates
    ) =
        loginState?.let {
            when (loginStates) {
                is LoginStates.Loading -> it.copy(
                    isLoading = true,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false
                )
                is LoginStates.Error -> it.copy(
                    isLoading = false,
                    isLoadError = true,
                    errorMessage = loginStates.errorMessage,
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false
                )
                is LoginStates.PasswordInvalid -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = true
                )
                is LoginStates.EmptyUser -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = true,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false
                )
                is LoginStates.EmptyPassword -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = false,
                    isPasswordEmpty = true,
                    isPasswordInvalid = false
                )
                is LoginStates.HasUser -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    userLogin = loginStates.userLogin,
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false
                )
            }
        } ?: LoginState()
}

interface LoginReducerContract {
    fun reducer(
        loginState: LoginState?,
        loginStates: LoginStates
    ): LoginState
}
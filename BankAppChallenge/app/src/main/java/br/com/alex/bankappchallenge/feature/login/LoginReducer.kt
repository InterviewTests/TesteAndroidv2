package br.com.alex.bankappchallenge.feature.login

class LoginReducer : LoginReducerContract {

    override fun reducer(
        currentState: LoginState?,
        nextState: LoginStates
    ) =
        currentState?.let {
            when (nextState) {
                is LoginStates.Loading -> it.copy(
                    isLoading = true,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false,
                    isUserInvalid = false
                )
                is LoginStates.Error -> it.copy(
                    isLoading = false,
                    isLoadError = true,
                    errorMessage = nextState.errorMessage,
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false,
                    isUserInvalid = false
                )
                is LoginStates.PasswordInvalid -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = true,
                    isUserInvalid = false
                )
                is LoginStates.EmptyUser -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = true,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false,
                    isUserInvalid = false
                )
                is LoginStates.EmptyPassword -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = false,
                    isPasswordEmpty = true,
                    isPasswordInvalid = false,
                    isUserInvalid = false
                )
                is LoginStates.HasUser -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    userLogin = nextState.userLogin,
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false,
                    isUserInvalid = false

                )
                is LoginStates.UserInvalid -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    isUserEmpty = false,
                    isPasswordEmpty = false,
                    isPasswordInvalid = false,
                    isUserInvalid = true
                )
            }
        } ?: LoginState()
}

interface LoginReducerContract {
    fun reducer(
        currentState: LoginState?,
        nextState: LoginStates
    ): LoginState
}
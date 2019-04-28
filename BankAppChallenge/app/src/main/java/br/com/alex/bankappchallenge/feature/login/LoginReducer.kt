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
                    errorMessage = ""
                )
                is LoginStates.Error -> it.copy(
                    isLoading = false,
                    isLoadError = true,
                    errorMessage = loginStates.errorMessage
                )
                is LoginStates.PasswordInvalid, LoginStates.EmptyFields -> it.copy(
                    isLoading = false,
                    isLoadError = true,
                    errorMessage = ""
                )
                is LoginStates.HasUser -> it.copy(
                    isLoading = false,
                    isLoadError = false,
                    errorMessage = "",
                    userLogin = loginStates.userLogin
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
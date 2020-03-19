package dev.ornelas.bankapp.ui.login

import dev.ornelas.banckapp.domain.interactors.*
import dev.ornelas.bankapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginPresenter(
    private val view: LoginContract.View,
    private val loginUser: LoginUser,
    private val validateUserName: ValidateUserName,
    private val validatePassword: ValidatePassword,
    private val saveLoggedUser: SaveLoggedUser,
    private val getLoggedUser: GetLoggedUser
) : LoginContract.Presenter {

    override fun onLogin(username: String, password: String) {
        val invalidUserName = validateUserName(username).isFailure
        val invalidPassword = validatePassword(password).isFailure

        if (invalidPassword || invalidUserName) {
            view.displayLoginResult(
                LoginViewModel(
                    usernameError = if (invalidUserName) R.string.invalid_username else null,
                    passwordError = if (invalidPassword) R.string.invalid_password else null
                )
            )
            return
        }

        handleLoad(username, password)
    }


    private fun handleLoad(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = loginUser(username, password)
            withContext(Dispatchers.Main) {
                if (result.isFailure) {
                    view.displayLoginResult(LoginViewModel(error = R.string.login_failed))
                } else {
                    result.data?.let {
                        saveLoggedUser(it)
                        view.displayLoginResult(LoginViewModel(success = UserToUIMapper.map(it)))
                    }
                }
            }
        }
    }


    override fun onViewCreated() {
        val user = getLoggedUser().data
        if (user != null) {
            view.displayLoginResult(LoginViewModel(success = UserToUIMapper.map(user)))
        }
    }

}
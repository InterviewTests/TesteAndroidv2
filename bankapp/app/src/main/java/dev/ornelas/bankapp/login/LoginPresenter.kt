package dev.ornelas.bankapp.login

import dev.ornelas.banckapp.domain.interactors.LoginUser
import dev.ornelas.banckapp.domain.interactors.SaveLoggedUser
import dev.ornelas.banckapp.domain.interactors.ValidatePassword
import dev.ornelas.banckapp.domain.interactors.ValidateUserName


class LoginPresenter(
    private val view: LoginContract.View,
    private val loginUser: LoginUser,
    private val validateUserName: ValidateUserName,
    private val validatePassword: ValidatePassword,
    private val saveLoggedUser: SaveLoggedUser
) : LoginContract.Presenter {

    override fun onLogin(username: String, password: String) {
        val invalidUserName = validateUserName(username).isFailure
        val invalidPassword = validatePassword(username).isFailure
        if (invalidUserName) {
            view.setUserNameError()
        }
        if (invalidPassword) {
            view.setUserPasswordError()
        }
    }

}
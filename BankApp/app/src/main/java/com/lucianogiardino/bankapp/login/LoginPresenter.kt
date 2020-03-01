package com.lucianogiardino.bankapp.login

import com.lucianogiardino.bankapp.login.domain.model.UserAccount
import com.lucianogiardino.bankapp.login.domain.usecase.HasLoggedUserUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.LoginUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.SaveLoggedUserUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.ValidateUserUseCase

class LoginPresenter(
    view: LoginContract.View,
    validateUserUseCase: ValidateUserUseCase,
    loginUseCase: LoginUseCase,
    hasLoggedUserUseCase: HasLoggedUserUseCase,
    saveLoggedUserUseCase: SaveLoggedUserUseCase) :
    LoginContract.Presenter, LoginContract.Presenter.OnLoginResponse,
    LoginContract.Presenter.OnValidateUserResponse {

    private var view: LoginContract.View = view
    private var validateUseCase = validateUserUseCase
    private var loginUseCase = loginUseCase
    private var hasLoggedUserUseCase = hasLoggedUserUseCase
    private var saveLoggedUserUseCase = saveLoggedUserUseCase


    override fun hasLoggedUser() {
        var hasUser = hasLoggedUserUseCase.execute();
        if(hasUser) view.goToStatement()
    }
    override fun validateUser(username: String, password: String) {
        validateUseCase.execute(this, username, password)
    }

    override fun onValidateUserResponseSuccess(username: String, password: String) {
        login(username, password)
    }

    override fun onValidateUserResponseFailed(msg: String) {
        view.showError(msg)
    }

    override fun login(username: String, password: String) {
        loginUseCase.execute(this,username,password)
    }

    override fun onLoginResponseSuccess(userAccount: UserAccount) {
        saveLoggedUserUseCase.execute(userAccount)
        view.goToStatement()
    }

    override fun onLoginResponseFailed(msg: String) {
        view.showError(msg)
    }


}
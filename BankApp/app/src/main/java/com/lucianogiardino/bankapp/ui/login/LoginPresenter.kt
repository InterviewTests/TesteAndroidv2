package com.lucianogiardino.bankapp.ui.login

import com.lucianogiardino.bankapp.domain.model.UserAccount
import com.lucianogiardino.bankapp.domain.usecase.HasLoggedUserUseCase
import com.lucianogiardino.bankapp.domain.usecase.LoginUseCase
import com.lucianogiardino.bankapp.domain.usecase.SaveLoggedUserUseCase
import com.lucianogiardino.bankapp.domain.usecase.ValidateUserUseCase

class LoginPresenter(
    private var view: LoginContract.View,
    private var validateUserUseCase: ValidateUserUseCase,
    private var loginUseCase: LoginUseCase,
    private var hasLoggedUserUseCase: HasLoggedUserUseCase,
    private var saveLoggedUserUseCase: SaveLoggedUserUseCase
) :
    LoginContract.Presenter, LoginContract.Presenter.OnLoginResponse,
    LoginContract.Presenter.OnValidateUserResponse {

    override fun hasLoggedUser() {
        var hasUser = hasLoggedUserUseCase.execute();
        if(hasUser) view.goToStatement()
    }
    override fun validateUser(username: String, password: String) {
        validateUserUseCase.execute(this, username, password)
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
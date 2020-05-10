package com.lucianogiardino.bankapp.presentation.login

import com.lucianogiardino.bankapp.domain.model.UserAccount

class LoginPresenter(
    private var view: LoginContract.View,
    private var validateUserUseCase: LoginContract.UseCase.ValidateUser,
    private var loginUseCase: LoginContract.UseCase.LoginUser,
    private var hasLoggedUserUseCase: LoginContract.UseCase.HasLoggedUser,
    private var saveLoggedUserUseCase: LoginContract.UseCase.SaveLoggedUser
) :
    LoginContract.Presenter, LoginContract.Presenter.OnLoginResponse,
    LoginContract.Presenter.OnValidateUserResponse {


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

    override fun hasLoggedUser() {
        var hasUser = hasLoggedUserUseCase.execute();
        if(hasUser) view.goToStatement()
    }


}
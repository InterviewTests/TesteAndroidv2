package com.lucianogiardino.bankapp.presentation.login

import com.lucianogiardino.bankapp.domain.model.UserAccount

class LoginPresenter(
    private var view: LoginContract.View,
    private var validatePasswordUseCase: LoginContract.UseCase.ValidatePassword,
    private var validateUsernameUseCase: LoginContract.UseCase.ValidateUsername,
    private var loginUseCase: LoginContract.UseCase.LoginUser,
    private var hasLoggedUserUseCase: LoginContract.UseCase.HasLoggedUser,
    private var saveLoggedUserUseCase: LoginContract.UseCase.SaveLoggedUser
) :
    LoginContract.Presenter, LoginContract.Presenter.OnLoginResponse,
    LoginContract.Presenter.OnValidatePasswordResponse,
    LoginContract.Presenter.OnValidateUsernameResponse{

    private var usernameIsValid = false
    private var passwordIsValid = false

    override fun validateUsername(username: String) {
        validateUsernameUseCase.execute(this,username)
    }

    override fun validatePassword(password: String) {
        validatePasswordUseCase.execute(this,password)
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

    override fun onValidateUsernameResponseSuccess(isValid: Boolean) {
        usernameIsValid = isValid
        view.showUsernameError(false,"")
        validateLoginfields()
    }

    override fun onValidateUsernameResponseFailed(msg: String) {
        view.showUsernameError(true,msg)
    }

    override fun onValidatePasswordResponseSuccess(isValid: Boolean) {
        passwordIsValid = isValid
        view.showPasswordError(false,"")
        validateLoginfields()
    }

    override fun onValidatePasswordResponseFailed(msg: String) {
        view.showPasswordError(true,msg)

    }

    private fun validateLoginfields(){
        if(passwordIsValid && usernameIsValid){
            view.enableLogin()
        }
    }




}
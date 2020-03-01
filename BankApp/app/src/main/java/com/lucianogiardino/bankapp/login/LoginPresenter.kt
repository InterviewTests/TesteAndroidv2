package com.lucianogiardino.bankapp.login

import android.content.Context
import android.widget.Toast
import com.lucianogiardino.bankapp.login.domain.model.UserAccount
import com.lucianogiardino.bankapp.login.domain.usecase.LoginUseCase
import com.lucianogiardino.bankapp.login.domain.usecase.ValidateUseCase

class LoginPresenter(
    view: LoginContract.View,
    validateUseCase: ValidateUseCase,
    loginUseCase: LoginUseCase,
    applicationContext: Context
) :
    LoginContract.Presenter, LoginContract.Presenter.OnLoginResponse,
    LoginContract.Presenter.OnValidateResponse {

    private var view: LoginContract.View = view
    private var validateUseCase = validateUseCase
    private var loginUseCase = loginUseCase
    private var applicationContext = applicationContext


    override fun validate(username: String, password: String) {
        validateUseCase.execute(this, username, password)
    }

    override fun onValidateResponseSuccess(username: String, password: String) {
        login(username, password)
    }

    override fun onValidateResponseFailed(msg: String) {
        view.showError(msg)
    }

    override fun login(username: String, password: String) {
        loginUseCase.execute(this,username,password)
    }

    override fun onLoginResponseSuccess(userAccount: UserAccount) {
        Toast.makeText(applicationContext,userAccount.name,Toast.LENGTH_LONG).show()
    }

    override fun onLoginResponseFailed(msg: String) {
        view.showError(msg)
    }


}
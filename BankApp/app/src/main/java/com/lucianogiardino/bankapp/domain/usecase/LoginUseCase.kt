package com.lucianogiardino.bankapp.domain.usecase

import com.lucianogiardino.bankapp.domain.model.UserAccount
import com.lucianogiardino.bankapp.presentation.login.LoginContract


class LoginUseCase(private val loginRepository: LoginContract.Repository): LoginContract.UseCase.LoginUser, LoginContract.UseCase.OnLoginUserResponse {

    private lateinit var listener: LoginContract.Presenter.OnLoginResponse

    override fun execute(listener: LoginContract.Presenter.OnLoginResponse, username: String, password: String){
        this.listener = listener
        loginRepository.login(this,username,password)
    }

    override fun onLoginResponseSuccess(userAccount: UserAccount) {
        listener.onLoginResponseSuccess(userAccount)
    }

    override fun onLoginResponseFailed(msg: String) {
        listener.onLoginResponseFailed(msg)
    }
}
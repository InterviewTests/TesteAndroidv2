package com.schaefer.bankapp.login_screen.presenter

import com.schaefer.bankapp.model.login.LoginModel

interface LoginPresenterInput {
    fun checkLastUser()
    fun makeLogin(loginModel: LoginModel)
}

package com.schaefer.bankapp.login_screen.activity

import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.user.UserModel

interface LoginActivityInput {
    fun showProgress()
    fun hideProgress()
    fun noUserLogged()
    fun successLogin(userModel: UserModel)
    fun errorLogin(errorResult: ErrorResult)
    fun errorGeneric(message: String)
}
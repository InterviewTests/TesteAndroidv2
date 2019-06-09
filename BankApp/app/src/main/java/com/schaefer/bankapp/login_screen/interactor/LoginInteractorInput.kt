package com.schaefer.bankapp.login_screen.interactor

import android.content.Context
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.login.LoginModel
import com.schaefer.bankapp.model.user.UserModel

interface LoginInteractorInput {
    fun checkLastUser(context: Context, finishedListener: FinishedListener)
    fun makeLogin(request: LoginModel, context: Context, finishedListener: FinishedListener)

    interface FinishedListener {
        fun noUserLogged()
        fun hasUserLogged(loginModel: LoginModel)
        fun successLogin(userModel: UserModel)
        fun errorLogin(errorResult: ErrorResult)
        fun genericError(message: String)
    }
}
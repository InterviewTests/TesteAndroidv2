package com.accenture.santander.login

import android.app.Activity
import android.graphics.drawable.Drawable
import com.accenture.santander.viewmodel.User
import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.Error
import com.accenture.santander.entity.UserAccount

class LoginContracts {

    interface LoginPresenterInput {
        fun searchLogo(context: Activity)
        fun searchData()
        fun login(user: User)
    }

    interface LoginInteractorInput {
        fun login(user: User)
        fun searchData()
        fun registerUser(auth: UserAccount, user: User)
    }

    interface LoginPresenterOutput {
        fun loadLogo(drawable: Drawable)
        fun invalideLogin()
        fun invalidePassword()
        fun resultData(user: User)
        fun goneProgress()
        fun visibleProgress()

        fun errorLogin()
        fun failLoadImage()
        fun failNetWork()
        fun failRequest()
        fun errorService(mensage: String?)
    }

    interface LoginInteractorOutput {
        fun sucessLogin()
        fun errorLogin(throwable: Throwable)
        fun errorMensage(mensage: String?)
        fun resultData(login: String, password: String)
        fun failNetWork()
        fun failResquest(code: Int)
        fun startLogged()
    }
}
package com.accenture.santander.login

import android.graphics.drawable.Drawable
import com.accenture.santander.viewmodel.User
import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.UserAccount

class LoginContracts {

    interface LoginPresenterInput {
        fun searchLogo()
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
        fun invalideLogin(mensage: String)
        fun invalidePassword(mensage: String)
        fun resultData(user: User)
    }

    interface LoginInteractorOutput {
        fun sucessLogin(auth: Auth?, user: User)
        fun errorLogin(throwable: Throwable)
        fun resultData(login: String, password: String)
        fun failNetWork()
        fun failResquest(code: Int)
        fun startLogged()
    }
}
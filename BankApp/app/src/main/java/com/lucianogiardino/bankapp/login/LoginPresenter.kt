package com.lucianogiardino.bankapp.login

import android.util.Log

class LoginPresenter(view: LoginContract.View): LoginContract.Presenter, LoginContract.Presenter.OnLoginResponse{

    private var view: LoginContract.View = view

    override fun validatePassword(password: String) {

        var regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+$).{1,}".toRegex()

        if(regex.matches(password)){
            login()
        }else{
            view.showError("Formato de senha inválido")
        }

    }

    override fun login() {
    }

    override fun onLoginResponseSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoginResponseFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
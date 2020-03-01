package com.lucianogiardino.bankapp.login

interface LoginContract {

    interface View{
        fun showError(msg: String)
        fun goToStatement()
    }

    interface Presenter{

        fun validatePassword(password: String)
        fun login()

        interface OnLoginResponse{
            fun onLoginResponseSuccess()
            fun onLoginResponseFailed()
        }

    }
}
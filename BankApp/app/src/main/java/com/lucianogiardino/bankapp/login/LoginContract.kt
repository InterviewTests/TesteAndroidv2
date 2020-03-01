package com.lucianogiardino.bankapp.login

import com.lucianogiardino.bankapp.login.domain.model.UserAccount

interface LoginContract {

    interface View{
        fun showError(msg: String)
        fun goToStatement()
    }

    interface Presenter{

        fun validate(username: String,password: String)

        interface OnValidateResponse{
            fun onValidateResponseSuccess(username: String, password: String)
            fun onValidateResponseFailed(msg: String)
        }
        fun login(username: String,password: String)
        interface OnLoginResponse{
            fun onLoginResponseSuccess(userAccount: UserAccount)
            fun onLoginResponseFailed(msg: String)
        }

    }
}
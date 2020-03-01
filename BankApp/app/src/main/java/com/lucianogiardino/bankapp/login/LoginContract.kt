package com.lucianogiardino.bankapp.login

import com.lucianogiardino.bankapp.login.domain.model.UserAccount

interface LoginContract {

    interface View{
        fun showError(msg: String)
        fun goToStatement()
    }

    interface Presenter{

        fun validateUser(username: String, password: String)
        interface OnValidateUserResponse{
            fun onValidateUserResponseSuccess(username: String, password: String)
            fun onValidateUserResponseFailed(msg: String)
        }

        fun login(username: String,password: String)
        interface OnLoginResponse{
            fun onLoginResponseSuccess(userAccount: UserAccount)
            fun onLoginResponseFailed(msg: String)
        }

        fun hasLoggedUser()

    }
}
package com.lucianogiardino.bankapp.presentation.login

import com.lucianogiardino.bankapp.domain.model.UserAccount

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

    interface UseCase{

        interface ValidateUser{
            fun execute(listener: Presenter.OnValidateUserResponse, username:String, password: String)
        }

        interface HasLoggedUser{
            fun execute(): Boolean
        }

        interface LoginUser{
            fun execute(listener: Presenter.OnLoginResponse, username: String, password: String)
        }

        interface OnLoginUserResponse{
            fun onLoginResponseSuccess(userAccount: UserAccount)
            fun onLoginResponseFailed(msg: String)
        }

        interface SaveLoggedUser{
            fun execute(userAccount: UserAccount)
        }
    }

    interface Repository{
        fun login(listener: UseCase.OnLoginUserResponse, username: String, password: String)
    }
}
package com.lucianogiardino.bankapp.presentation.login

import com.lucianogiardino.bankapp.domain.model.UserAccount

interface LoginContract {

    interface View{
        fun showError(msg: String)
        fun showUsernameError(hasError:Boolean, msg: String)
        fun showPasswordError(hasError:Boolean, msg: String)
        fun enableLogin()
        fun goToStatement()
    }

    interface Presenter{

        fun validateUsername(username: String)

        interface OnValidateUsernameResponse{
            fun onValidateUsernameResponseSuccess(isValid: Boolean)
            fun onValidateUsernameResponseFailed(msg: String)
        }

        fun validatePassword(password: String)

        interface OnValidatePasswordResponse{
            fun onValidatePasswordResponseSuccess(isValid: Boolean)
            fun onValidatePasswordResponseFailed(msg: String)
        }

        fun login(username: String,password: String)
        interface OnLoginResponse{
            fun onLoginResponseSuccess(userAccount: UserAccount)
            fun onLoginResponseFailed(msg: String)
        }

        fun hasLoggedUser()

    }

    interface UseCase{

        interface ValidatePassword{
            fun execute(listener: Presenter.OnValidatePasswordResponse, password: String)
        }

        interface ValidateUsername{
            fun execute(listener: Presenter.OnValidateUsernameResponse, username: String)
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
package br.com.rms.bankapp.ui.login

import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.utils.validations.ValidationException

interface LoginContract : BaseContract.View{
    interface View : BaseContract.View{
        fun getUser(): String
        fun getPassword(): String
        fun onValidationException(e: ValidationException)
        fun showLoader()
        fun hideLoader()
        fun showErrorMessage(error_message_request_login: Int)
        fun loginSuccess()
        fun setUser(user: String?)

    }

    interface Presenter : BaseContract.Presenter<View>{
        fun login()
        fun loadUserData()

    }
}
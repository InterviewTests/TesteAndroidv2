package br.com.rms.bankapp.ui.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.utils.validations.ValidationException

interface LoginContract : BaseContract.View{

    interface View : BaseContract.View, LifecycleOwner {
        override fun getLifecycle(): Lifecycle
        fun validateError(e: ValidationException)
        fun showLoader()
        fun hideLoader()
        fun showErrorMessage(error_message_request_login: Int)
        fun loginSuccess()
        fun setUser(user: String?)

    }

    interface Presenter : BaseContract.Presenter{
        fun login(userLogin: String, userPassword: String)
    }
}
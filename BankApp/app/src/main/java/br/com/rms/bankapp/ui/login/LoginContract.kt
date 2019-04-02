package br.com.rms.bankapp.ui.login

import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.utils.validations.ValidationException

interface LoginContract : BaseContract.View{
    interface View : BaseContract.View{
        fun getUser(): String
        fun getPassword(): String
        fun onValidationException(e: ValidationException)

    }

    interface Presenter : BaseContract.Presenter<View>{
        fun login()

    }
}
package com.br.projetotestesantanter.loginscreen

import android.widget.EditText
import com.br.projetotestesantanter.BasePresenter
import com.br.projetotestesantanter.BaseView
import com.br.projetotestesantanter.api.model.LoginResponse

interface LoginContract {

    interface View : BaseView {


        fun openScreenStatement(loginResponse: LoginResponse)
    }


    interface Presenter : BasePresenter {

        fun setEditUser(editUser: EditText)
        fun setEditPassword(editPassword : EditText)
        fun logar()
        fun doLogin(loginModel: LoginModel)
        fun attachView(view: View)
    }
}
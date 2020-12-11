package br.com.testeandroidv2.presenter.login

import android.content.Context

import br.com.testeandroidv2.model.bean.LoginBean

class MVP {
    interface Model {
        fun loadLogin(user: String, password: String)
    }

    interface Presenter {
        val context: Context
        fun setView(view: View)
        fun showProgressBar(status: Boolean)
        fun loadLogin(user: String, password: String)
        fun updateData(loginBean: LoginBean)
    }

    interface View {
        fun showProgressBar(visible: Int)
        fun updateData(loginBean: LoginBean)
    }
}
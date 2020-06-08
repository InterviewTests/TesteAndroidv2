package br.com.testeandroidv2.presenter.login

import android.content.Context
import android.view.View
import br.com.testeandroidv2.model.bean.LoginBean

import br.com.testeandroidv2.model.login.Model

class Presenter : MVP.Presenter {
    private lateinit var view: MVP.View

    private val model: MVP.Model = Model(this)

    override val context: Context
        get() = view as Context

    override fun setView(view: MVP.View) { this.view = view }
    override fun showProgressBar(status: Boolean) {
        val visible: Int = if (status) View.VISIBLE else View.GONE
        view.showProgressBar(visible)
    }

    override fun loadLogin(user: String, password: String) {
        model.loadLogin(user, password)
    }

    override fun updateData(loginBean: LoginBean) {
        view.updateData(loginBean)
    }
}
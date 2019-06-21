package com.santander.app.feature.login

import com.santander.app.core.ui.base.BasePresenter
import com.santander.app.core.ui.base.BaseView

object LoginContract {

    interface View: BaseView {
        fun displayUser(user: String)
        fun onAuthenticationSuccess()
    }

    interface Presenter: BasePresenter<View> {
        fun doLogin(user: String, password: String)
    }
}
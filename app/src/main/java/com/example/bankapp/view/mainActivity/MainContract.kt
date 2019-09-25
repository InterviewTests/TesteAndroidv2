package com.example.bankapp.view.mainActivity

import com.example.bankapp.model.User

interface MainContract{
    interface View {
        fun logged(user: User)
        fun showMessage(message: String)
    }

    interface Presenter {
        fun doLogin(password: String, user: String)
        fun getLogged(): String?
    }
}
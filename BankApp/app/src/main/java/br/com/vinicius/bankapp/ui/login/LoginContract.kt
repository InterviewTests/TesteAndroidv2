package br.com.vinicius.bankapp.ui.login

import br.com.vinicius.bankapp.domain.User

class LoginContract {

    interface View {
        fun notification(message: String)

        fun saveUserPreferences(user: User)

        fun showProgressBar(show: Boolean)
    }

    interface Presenter {
        val view: LoginContract.View

        fun startLogin(username: String, password:String)
    }
}
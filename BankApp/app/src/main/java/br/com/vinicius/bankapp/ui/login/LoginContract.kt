package br.com.vinicius.bankapp.ui.login

import br.com.vinicius.bankapp.domain.User

class LoginContract {

    interface View {
        fun notification(message: String)

        fun saveUserPreferences(user: User)
    }

    interface Presenter {
        fun startLogin(username: String, password:String)
    }
}
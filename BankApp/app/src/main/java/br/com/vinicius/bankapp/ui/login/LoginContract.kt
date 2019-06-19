package br.com.vinicius.bankapp.ui.login

class LoginContract {

    interface View {
        fun notification(message: String)
    }

    interface Presenter {
        fun startLogin(username: String, password:String)
    }
}
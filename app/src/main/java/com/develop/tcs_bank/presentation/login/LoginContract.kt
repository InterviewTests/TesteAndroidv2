package com.develop.tcs_bank.presentation.login

class LoginContract {
    interface View {
        fun showMessage(msg: String)
        fun setUser(us: String)
        fun setPass(ps: String)
        fun navigate()
    }
    interface Presenter {
        //fun callLogin(user: String, pass: String)
        fun processMsg(msg: String)
        fun checkLogin(): String
        fun checkPass(): String
        fun processLogin(user: String, pass: String)
    }
}
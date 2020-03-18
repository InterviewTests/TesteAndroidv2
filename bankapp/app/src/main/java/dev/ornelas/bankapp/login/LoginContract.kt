package dev.ornelas.bankapp.login

interface LoginContract {
    interface View {
        fun navigateToStatement(loginViewModel: LoginViewModel)
        fun displaySavedUser(username: String)
        fun setUserNameError()
        fun setUserPasswordError()
    }

    interface Presenter {
        fun onLogin(username: String, password: String)
    }
}
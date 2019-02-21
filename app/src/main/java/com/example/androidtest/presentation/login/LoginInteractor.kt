package com.example.androidtest.presentation.login

import com.example.androidtest.repository.SuccessResponse


interface LoginInteractorContract {
    fun requestLogin(user: String, pass: String)
}

class LoginInteractor(private val presenter: LoginPresenterContract) : LoginInteractorContract {

    private var repository: LoginRepository = LoginRepository()

    override fun requestLogin(user: String, pass: String) {

        if (user.isBlank() || pass.isBlank()) {
            presenter.invalidInputForm()
            return
        }

        repository.loginCall(user, pass) {
            when (it) {
                is SuccessResponse -> {
                    presenter.loginSuccessfull()
                }

                else -> {
                    presenter.loginFailed(it.message)
                }
            }
        }


    }

}

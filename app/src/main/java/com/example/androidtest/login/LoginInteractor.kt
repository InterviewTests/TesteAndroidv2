package com.example.androidtest.login

import com.example.androidtest.api.SuccessResponse


interface LoginInteractorContract {
    fun requestLogin(user: String, pass: String)
}

class LoginInteractor(
    private val presenter: LoginPresenterContract,
    private val repository: LoginRepository
) : LoginInteractorContract {

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

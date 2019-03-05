package com.example.androidtest.presentation.login

import android.content.Context
import com.example.androidtest.repository.Repository
import com.example.androidtest.repository.SuccessResponse


interface LoginInteractorContract {
    fun requestLogin(user: String, pass: String)
}

class LoginInteractor(
    private val context: Context,
    private val presenter: LoginPresenterContract
) : LoginInteractorContract {

    override fun requestLogin(user: String, pass: String) {

        if (user.isBlank() || pass.isBlank()) {
            presenter.invalidInputForm()
            return
        }

        presenter.requestInProgress()
        Repository.loginCall(context, user, pass) {
            when (it) {
                is SuccessResponse -> {
                    presenter.loginSuccessfull()
                }

                else -> {
                    presenter.loginFailed(it.msg)
                }
            }
        }

    }

}

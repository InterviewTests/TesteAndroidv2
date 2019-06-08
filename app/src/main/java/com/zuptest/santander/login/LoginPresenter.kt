package com.zuptest.santander.login

import android.util.Log
import com.zuptest.santander.domain.business.model.Credentials
import com.zuptest.santander.domain.business.usecase.DoLoginUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginPresenter(
    val view: LoginContract.View,
    val doLoginUseCase: DoLoginUseCase
) : LoginContract.Presenter {

    override fun checkLoginType(login: CharSequence?) {
        login?.let {
            if (it[0].isLetter()) {
                view.applyEmailLogin()
            } else {
                view.applyCPFLogin()
            }
        }
    }

    override fun doLogin(login: String?, password: String?) {
        validateLogin(login)
        validatePassword(password)

        doLoginUseCase.execute(Credentials(login!!, password!!))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    view.launchStatementsScreen(it)
                },
                onError = {
                    Log.d("error", it.message)
                }
            )
    }

    private fun validateLogin(login: String?) {
        login?.let {
            if (it.isBlank())
                view.displayInvalidEmailLoginFeedBack()
            return
        }
    }

    private fun validatePassword(password: String?) {
        password?.let {
            if (it.isBlank())
                view.displayEmptyPasswordFeedBack()
            return
        }
    }
}
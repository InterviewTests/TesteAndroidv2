package com.zuptest.santander.login

import android.util.Log
import com.zuptest.santander.domain.business.model.Credentials
import com.zuptest.santander.domain.business.model.PasswordValidator
import com.zuptest.santander.domain.business.usecase.DoLoginUseCase
import com.zuptest.santander.domain.business.usecase.RetrieveLastLoginUseCase
import com.zuptest.santander.domain.business.usecase.SaveSuccessfulLoginInfoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginPresenter(
    private val view: LoginContract.View,
    private val doLoginUseCase: DoLoginUseCase,
    private val saveSuccessfulLoginInfoUseCase: SaveSuccessfulLoginInfoUseCase,
    private val retrieveLastLoginUseCase: RetrieveLastLoginUseCase
) : LoginContract.Presenter {

    override fun checkPreviousLogin() {
        retrieveLastLoginUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    if (it.isNotBlank()) {
                        view.displayLastLogin(it)
                    }
                }
            )
    }

    override fun checkLoginType(login: CharSequence?) {
        login?.let {
            if (it[0].isLetter()) {
                view.applyEmailLogin()
            } else {
                view.applyCPFLogin()
            }
        }
    }

    override fun doLogin(login: String, password: String) {

        if (PasswordValidator.isValid(password)) {
            doLoginUseCase.execute(Credentials(login, password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        saveSuccessfulLoginInfoUseCase.execute(login)
                        view.launchStatementsScreen(it)
                    },
                    onError = {
                        Log.d("error", it.message)
                    }
                )
        } else {
            view.displayInvalidPasswordFeedBack()
        }
    }
}
package com.santander.app.feature.login

import android.util.Log
import com.santander.app.R
import com.santander.app.core.ui.base.AbstractPresenter
import com.santander.app.core.util.extension.defaultSchedulers
import com.santander.app.core.util.validation.*
import com.santander.domain.entity.input.SessionQuery
import com.santander.domain.usecase.IGetUserUseCase
import com.santander.domain.usecase.ILoginUseCase
import io.reactivex.rxkotlin.subscribeBy

class LoginPresenter(
    override var view: LoginContract.View,
    private val loginUseCase: ILoginUseCase,
    private val getUserUseCase: IGetUserUseCase
) : AbstractPresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun start() {
        launch {
            getUserUseCase.execute()
                .defaultSchedulers()
                .subscribeBy(
                    onNext = {
                        view.displayUser(it)
                    },
                    onError = {
                        Log.i(javaClass.simpleName, "Nothing needed: ${it.message}")
                    }
                )
        }
    }

    override fun doLogin(user: String, password: String) {

        launch {

            val validations = listOf(
                (user.isNotEmpty() && password.isNotEmpty()) to view.getResource(R.string.error_empty_fields),
                (user.extractNumbers().isValidCPF() || user.isValidEmail()) to view.getResource(R.string.error_invalid_user_password),
                (password.isValidPassword()) to view.getResource(R.string.error_invalid_password)
            )

            ValidationManager.validation(validations = validations)
                .andThen(loginUseCase.execute(params = SessionQuery.SignIn(user = user, password = password)))
                .defaultSchedulers()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .subscribeBy(
                    onComplete = {
                        view.onAuthenticationSuccess()
                    },
                    onError = {
                        view.handleError(it)
                    }
                )
        }
    }

}
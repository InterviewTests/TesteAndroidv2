package br.com.santander.android.bank.login.presentation

import br.com.santander.android.bank.core.extensions.request
import br.com.santander.android.bank.login.domain.interactor.LoginFailureUseCase
import br.com.santander.android.bank.login.domain.interactor.LoginInteractor
import br.com.santander.android.bank.login.domain.model.UserAccount
import br.com.santander.android.bank.login.domain.model.UserLogin
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.UnknownHostException

internal class LoginPresenter(private val view: LoginContract.View,
                              private val loginInteractor: LoginInteractor): LoginContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun requestLogin(login: String, password: String) {
        val userLogin = UserLogin(login, password)
        disposable.request(
            loginInteractor.login(userLogin),
            { view.onLoginSuccess(it) },
            { showError(it) },
            { view.hideLoading() }
        )
    }

    override fun saveSession(userAccount: UserAccount) = loginInteractor
        .saveSession(userAccount)

    override fun getSavedSession() = loginInteractor
        .getSavedSession()

    private fun showError(error: Throwable) {
        when(error) {
            is LoginFailureUseCase.EmptyUser -> view.showEmptyUserError()
            is LoginFailureUseCase.EmptyPassword -> view.showEmptyPasswordError()
            is LoginFailureUseCase.MalformattedPassword -> view.showInvalidPasswordError()
            is UnknownHostException -> view.showOfflineMessage()
            is HttpException -> view.showTryAgainMessage()
        }
    }

    override fun onDestroy() { if(!disposable.isDisposed) disposable.dispose() }

}
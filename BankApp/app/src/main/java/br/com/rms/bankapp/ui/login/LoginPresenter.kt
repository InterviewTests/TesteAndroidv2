package br.com.rms.bankapp.ui.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.mvp.BasePresenter
import br.com.rms.bankapp.data.repository.user.UserRepository
import br.com.rms.bankapp.data.repository.user.UserRepositoryContract
import br.com.rms.bankapp.utils.validations.ValidationException
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val loginView: LoginContract.View,
    private val userRepository: UserRepositoryContract
) : BasePresenter<LoginContract.View>(loginView), LoginContract.Presenter {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadUserData() {
        val userName = userRepository.loadUserName()
        userName.observe(loginView, Observer<String> {
            if (!it.isNullOrEmpty()) {
                loginView.setUser(it)
            }
        })

    }

    override fun login(userLogin: String, userPassword: String) {
        userRepository.login(userLogin, userPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    loginView.hideLoader()
                    loginView.loginSuccess()
                }

                override fun onSubscribe(d: Disposable) {
                    addDisposable(d)
                    loginView.showLoader()
                }

                override fun onError(e: Throwable) {
                    if (e is ValidationException) {
                        loginView.validateError(e)
                    } else {
                        e.message?.let {message ->
                            loginView.showErrorMessage(message)
                        }

                    }
                    loginView.hideLoader()

                }
            })
    }
}
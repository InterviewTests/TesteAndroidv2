package com.accenture.primo.bankapp.ui.login

import com.accenture.primo.bankapp.extension.isNotNull
import com.accenture.primo.bankapp.model.User
import com.accenture.primo.bankapp.ui.login.contracts.ILoginInteractor
import com.accenture.primo.bankapp.ui.login.contracts.ILoginPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginInteractor(private val _presenter: ILoginPresenter) : ILoginInteractor {
    private val _worker by lazy{ LoginWorker() }

    override fun login(user: String, password: String) {
        _presenter.showLoading()
        val request = LoginModel.LoginRequest(user, password)
        val objObservable = _worker.doLogin(request)

        objObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate{
                _presenter.hideLoading()
            }
            .subscribe( {
                if (it.error.menssage.isNotNull())
                    _presenter.onError(it.error.menssage)
                else {
                    //SAVE PREFERENCES
                    val pref: LoginModel.LoginViewModel = LoginModel.LoginViewModel(it.user, request)
                    _worker.savePreferences(_presenter.getContext(), pref)

                    //UPDATE UI
                    _presenter.onSuccess(it.user)
                }

            },{
                _presenter.onError(it.message!!)
                it.printStackTrace()
            })
    }

    override fun readPreferences() {
        val pref:  LoginModel.LoginViewModel? = _worker.readPreferences(_presenter.getContext())

        if (pref.isNotNull())
            _presenter.showPreferences(pref!!)
    }
}

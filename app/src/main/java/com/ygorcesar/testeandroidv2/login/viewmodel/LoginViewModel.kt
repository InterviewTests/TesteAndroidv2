package com.ygorcesar.testeandroidv2.login.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.ygorcesar.testeandroidv2.base.data.ViewState
import com.ygorcesar.testeandroidv2.base.extensions.addToComposite
import com.ygorcesar.testeandroidv2.base.extensions.observeOnComputation
import com.ygorcesar.testeandroidv2.base.presentation.BaseViewModel
import com.ygorcesar.testeandroidv2.login.domain.LoginInteractor
import com.ygorcesar.testeandroidv2.managers.SessionManager
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val sessionManagerInject: SessionManager.SessionManagerInject
) : BaseViewModel() {

    val loginResponseState = MutableLiveData<ViewState>()

    fun login(user: String, password: String) {
        compositeDisposable.clear()

        loginInteractor.login(user, password)
            .observeOnComputation()
            .doOnSubscribe { loginResponseState.postValue(ViewState.Loading) }
            .subscribe({ userAccount ->
                sessionManagerInject.setCurrentUserAccount(userAccount)
                loginResponseState.postValue(ViewState.Success)
            }, { error -> handleFailure(error) })
            .addToComposite(compositeDisposable)
    }
}
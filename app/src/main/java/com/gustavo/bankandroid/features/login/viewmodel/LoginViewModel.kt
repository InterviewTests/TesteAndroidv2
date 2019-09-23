package com.gustavo.bankandroid.features.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gustavo.bankandroid.base.BaseViewModel
import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.features.login.usecase.LoginUseCases
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase,
    private val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase
) : BaseViewModel() {

    override val compositeDisposable = CompositeDisposable()

    private val _loginSuccessLiveData = MutableLiveData<Boolean>()
    val loginSuccessLiveData: LiveData<Boolean>
        get() = _loginSuccessLiveData

    fun login(username: String, password: String) {
        val disposable = authenticateUserUseCase.execute(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it is LoginResponse.Success) {
                    storeUser(it.userInfo)
                    _loginSuccessLiveData.value = true
                } else {
                    _loginSuccessLiveData.value = false
                }
            }, {
                _loginSuccessLiveData.value = false
            })
        compositeDisposable.add(disposable)
    }

    private fun storeUser(userInfo: UserInfo) {
        val disposable = Completable.create { storeUserInfoUseCase.execute(userInfo) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(disposable)
    }

}
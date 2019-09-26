package com.gustavo.bankandroid.features.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gustavo.bankandroid.base.BaseViewModel
import com.gustavo.bankandroid.contants.Constants
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
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

    private val _validUsernameLiveData = MutableLiveData<Boolean>()
    val validUsernameLiveData: LiveData<Boolean>
        get() = _validUsernameLiveData

    private val _validPasswordLiveData = MutableLiveData<Boolean>()
    val validPasswordLiveData: LiveData<Boolean>
        get() = _validPasswordLiveData

    fun login(username: String, password: String) {
        if (checkValidUserName(username) && checkValidPassword(password)) {
            val disposable = authenticateUserUseCase.execute(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it is UserLoginResponse.Success) {
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
    }

    private fun checkValidUserName(username: String): Boolean {
        val matchCpf = username.matches(Constants.cpfRegex.toRegex())
        val matchEmail= username.matches(Constants.emailRegex.toRegex())
        val match = (matchCpf || matchEmail)
        _validUsernameLiveData.value = match
        return match
    }

    private fun checkValidPassword(password: String): Boolean {
        val match = password.matches(Constants.strongPasswordRegex.toRegex())
        _validPasswordLiveData.value = match
        return match
    }

    private fun storeUser(userInfo: UserInfo) {
        val disposable = Completable.create { storeUserInfoUseCase.execute(userInfo) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(disposable)
    }

}
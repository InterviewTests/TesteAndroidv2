package com.gustavo.bankandroid.features.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gustavo.bankandroid.common.base.BaseViewModel
import com.gustavo.bankandroid.domain.contracts.LoginUseCases
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase,
    private val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase,
    private val validateUserName: LoginUseCases.ValidateUserName,
    private val validatePassword: LoginUseCases.ValidatePassword
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

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    fun login(username: String, password: String) {
        if (checkValidUserName(username) && checkValidPassword(password)) {
            _isLoadingLiveData.value = true
            val disposable = authenticateUserUseCase.execute(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it is UserLoginResponse.Success) {
                        storeUser(it.userInfo)
                        _loginSuccessLiveData.value = true
                        _isLoadingLiveData.value = false
                    } else {
                        _loginSuccessLiveData.value = false
                        _isLoadingLiveData.value = false
                    }
                }, {
                    _loginSuccessLiveData.value = false
                })
            compositeDisposable.add(disposable)
        }
    }

    private fun checkValidUserName(username: String): Boolean {
        val match = validateUserName(username)
        _validUsernameLiveData.value = match
        return match
    }

    private fun checkValidPassword(password: String): Boolean {
        val match = validatePassword(password)
        _validPasswordLiveData.value = match
        return match
    }

    private fun storeUser(userInfo: UserInfo) {
        val disposable = Completable.create { storeUserInfoUseCase.execute(userInfo) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {})
        compositeDisposable.add(disposable)
    }

}
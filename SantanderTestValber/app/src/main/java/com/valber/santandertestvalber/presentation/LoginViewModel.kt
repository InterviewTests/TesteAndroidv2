package com.valber.santandertestvalber.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valber.domain2.LoginCase
import com.valber.data.model.UserAccount
import com.valber.santandertestvalber.extensions.setError
import com.valber.santandertestvalber.extensions.setLoading
import com.valber.santandertestvalber.extensions.setSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val loginCase: LoginCase
) : ViewModel() {
    val login = MutableLiveData<Resource<UserAccount>>()

    private val compositeDisposable = CompositeDisposable()

    fun logar(user: String, password: String) = compositeDisposable.add(
        loginCase.logar(user, password)
            .doOnSubscribe { login.setLoading() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ login.setSuccess(it) }, { login.setError(it.message) })
    )


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
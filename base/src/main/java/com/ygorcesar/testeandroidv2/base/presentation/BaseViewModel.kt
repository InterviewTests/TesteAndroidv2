package com.ygorcesar.testeandroidv2.base.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ygorcesar.testeandroidv2.base.common.exception.AppException
import com.ygorcesar.testeandroidv2.base.common.exception.UnknownException
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val appException = MutableLiveData<AppException>()

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun handleFailure(appException: Throwable) {
        if (appException is AppException) {
            this.appException.postValue(appException)
        } else {
            this.appException.postValue(UnknownException)
        }
        Timber.e(appException)
    }
}
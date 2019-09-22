package com.gustavo.bankandroid.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel:ViewModel() {
    abstract val compositeDisposable: CompositeDisposable

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
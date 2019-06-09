package com.androiddeveloper.santanderTest.shared.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseInteractor : IBaseContract.Presenter {

    var compositeDisposable: CompositeDisposable? = null

    override fun unsubscribe() {
        compositeDisposable = CompositeDisposable()
    }

    override fun createCompositeDisposable() {
        compositeDisposable?.clear()
    }
}
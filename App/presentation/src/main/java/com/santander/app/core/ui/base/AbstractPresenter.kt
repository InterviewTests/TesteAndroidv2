package com.santander.app.core.ui.base

import io.reactivex.disposables.Disposable

abstract class AbstractPresenter<V : BaseView> : BasePresenter<V> {

    override lateinit var view: V

    fun launch(job: () -> Disposable) {
        view.addDisposable(job())
    }

    override fun start() {

    }
}
package com.example.testesantander.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent


abstract class BasePresenter <V: Contract.View>: Contract.Presenter<V> {

    protected var mView: V? = null

    override fun attach(view: V) {
        this.mView = view
        view.getLifecycle()?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun detach() {
        this.mView = null
    }

}
package br.com.rms.bankapp.base.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

interface BaseContract {

    interface View {
        fun getLifecycle() : Lifecycle
    }

    interface Presenter : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun attach()
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun detach()
    }
}
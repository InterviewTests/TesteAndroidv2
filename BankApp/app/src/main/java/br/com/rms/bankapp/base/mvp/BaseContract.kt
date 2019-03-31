package br.com.rms.bankapp.base.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface BaseContract {

    interface View {
        fun getLifecycle() : Lifecycle
    }

    interface Presenter<V: View> : LifecycleObserver {
        fun attach(view : V)
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun detach()
    }
}
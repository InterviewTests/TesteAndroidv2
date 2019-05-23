package com.example.testesantander.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface Contract {
    interface Presenter<V : View> : LifecycleObserver {
        fun attach(view: V)
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun detach()
    }

    interface View {
        fun getLifecycle(): Lifecycle?
    }
}
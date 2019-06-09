package com.androiddeveloper.santanderTest.shared.base

interface IBaseContract {

    interface Presenter{
        fun unsubscribe()
        fun createCompositeDisposable()
    }
}
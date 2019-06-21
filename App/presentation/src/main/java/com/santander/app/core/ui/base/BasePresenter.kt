package com.santander.app.core.ui.base

interface BasePresenter<T> {
    fun start()
    var view: T
}
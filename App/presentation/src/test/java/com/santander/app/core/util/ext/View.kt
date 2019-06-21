package com.santander.app.core.util.ext

import com.nhaarman.mockitokotlin2.verify
import com.santander.app.core.ui.base.BaseView

fun BaseView.verifyIfProgressShowed() {
    verify(this).showLoading()
}

fun BaseView.verifyIfProgressHided() {
    verify(this).hideLoading()
}
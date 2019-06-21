package com.santander.app.core.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import io.reactivex.disposables.Disposable

interface BaseView {
    @LayoutRes
    fun getLayoutRes(): Int
    fun initView(savedInstanceState: Bundle?)
    fun showMessage(message: String)
    fun showLoading()
    fun hideLoading()
    fun addDisposable(disposable: Disposable)
    fun hideKeyboard()
    fun getResource(@StringRes resId: Int) : String
    fun displayLogout(action: (() -> Unit)? = null)
    fun logout()
    fun handleError(error: Throwable)
}
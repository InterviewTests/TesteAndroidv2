package com.ygorcesar.testeandroidv2.base.presentation

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ygorcesar.testeandroidv2.base.common.exception.*
import com.ygorcesar.testeandroidv2.base.common.network.ResponseError
import com.ygorcesar.testeandroidv2.base.data.preferences.PreferencesHelper
import com.ygorcesar.testeandroidv2.base.di.base
import com.ygorcesar.testeandroidv2.base.extensions.toast
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutResId: Int
    open val showAutomaticMessage: Boolean = true
    open val needAuthentication: Boolean = false

    private val preferencesHelper: PreferencesHelper by lazy { base().preferencesHelper }
    val viewModelFactory: ViewModelProvider.Factory by lazy {
        base().viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        onInit()
    }

    /**
     * Function called onCreate Activity after setContentView
     * */
    abstract fun onInit()

    fun isAuthenticated() = preferencesHelper.user.isNotBlank()

    fun needAuthentication() = !isAuthenticated() && needAuthentication

    /**
     * Check commons exception between application
     *
     * @param appException The exception returned from backend
     * @param body The function to check specific business exceptions
     */
    fun checkResponseException(
        appException: AppException?,
        body: (AppException?) -> Unit
    ) {
        Timber.e(appException)
        when (appException) {
            NetworkError -> {
                onNetworkWithoutConnection()
            }
            UnauthorizedError -> {
                onUnAuthorized()
            }
            HttpError -> {
                onHttpError()
            }
            UnknownException -> {
                onUnknownError()
            }
            InvalidCredentials -> onInvalidCredentials()
            is ServerError -> {
                onResponseError(appException.error)
            }
            else -> {
                body(appException)
            }
        }
    }

    /**
     * Function called when handled a Http generic exception
     */
    open fun onHttpError() {}

    /**
     * Function called when there is no internet connection
     */
    open fun onNetworkWithoutConnection() {

    }

    open fun showServerError(error: ResponseError, duration: Int = 3000) {
        error.message?.let { toast(it) }
    }

    /**
     * Function called when user session expired or user is invalid
     */
    open fun onUnAuthorized() {}

    open fun onInvalidCredentials() {

    }

    open fun onResponseError(error: ResponseError) {
        Timber.e(error.toString())
        if (showAutomaticMessage) {
            showServerError(error)
        }
    }

    open fun onUnknownError() {

    }

    open fun loading(isLoading: Boolean) {

    }
}
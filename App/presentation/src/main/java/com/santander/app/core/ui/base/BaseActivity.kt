package com.santander.app.core.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.santander.app.R
import com.santander.app.core.util.extension.ActionButton
import com.santander.app.core.util.extension.displayDialog
import com.santander.app.core.util.extension.hideSoftInputFromWindow
import com.santander.app.core.util.validation.ValidationException
import com.santander.app.feature.login.LoginActivity
import com.santander.data.exception.ApiException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.io.IOException

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initView(savedInstanceState = savedInstanceState)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        displayDialog(message = message)
    }

    override fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposables.isDisposed) {
            disposables.clear()
        }
    }

    override fun getResource(resId: Int): String = getString(resId)

    override fun handleError(error: Throwable) {
        when (error) {
            is IOException -> {
                displayDialog(
                    title = getString(R.string.default_error_title),
                    message = getString(R.string.error_no_network)
                )
            }
            is ValidationException -> {
                showMessage(message = error.message.orEmpty())
            }
            is ApiException -> {
                displayDialog(
                    title = getString(R.string.default_error_title),
                    message = error.message ?: getString(R.string.default_error_message)
                )
            }
            else -> {
                displayDialog(
                    title = getString(R.string.default_error_title),
                    message = getString(R.string.default_error_message)
                )
            }
        }
    }

    override fun hideKeyboard() {
        this.hideSoftInputFromWindow()
    }

    override fun displayLogout(action: (() -> Unit)?) {
        displayDialog(
            title = getString(R.string.logout_title),
            message = getString(R.string.logout_message),
            positiveAction = ActionButton(content = getString(R.string.action_yes), action = {
                action?.invoke()
            }),
            negativeAction = ActionButton(content = getString(R.string.action_cancel))
        )
    }

    override fun logout() {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

}
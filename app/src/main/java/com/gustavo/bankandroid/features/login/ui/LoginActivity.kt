package com.gustavo.bankandroid.features.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gustavo.bankandroid.R
import com.gustavo.bankandroid.features.login.injection.LoginInjection
import com.gustavo.bankandroid.features.login.injection.LoginModule
import com.gustavo.bankandroid.features.login.injection.LoginModuleImpl
import com.gustavo.bankandroid.features.login.viewmodel.LoginViewModel
import com.gustavo.bankandroid.features.login.viewmodel.LoginViewModelFactory
import com.gustavo.bankandroid.features.statements.ui.StatementActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.listeners.onClick

class LoginActivity : AppCompatActivity(), LoginInjection {
    override val loginModule: LoginModule by lazy {
        LoginModuleImpl(this)
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = getViewModel()
        setListeners()
        serObservers()
    }

    private fun serObservers() {
        viewModel.loginSuccessLiveData.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, StatementActivity::class.java))
            } else {
                showError()
            }
        })
        viewModel.validPasswordLiveData.observe(this, Observer {
            if (it) {
                passwordEditText.error = null
            } else {
                passwordEditText.error = getString(R.string.invalid_password_error)
            }
        })
        viewModel.validUsernameLiveData.observe(this, Observer {
            if (it) {
                userEditText.error = null
            } else {
                userEditText.error = getString(R.string.invalid_username_error)
            }
        })
    }

    private fun showError() {
        val dialog = AlertDialog.Builder(this)
            .create()
        dialog.setTitle(getString(R.string.login_dialog_error_title))
        dialog.setMessage(getString(R.string.login_dialog_error_message))
        dialog.show()
    }

    private fun setListeners() {
        loginButton.onClick {
            viewModel.login(userEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun getViewModel() =
        ViewModelProviders.of(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)

}

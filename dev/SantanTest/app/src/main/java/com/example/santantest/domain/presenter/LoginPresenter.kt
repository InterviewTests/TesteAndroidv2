package com.example.santantest.domain.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.santantest.R
import com.example.santantest.domain.interactor.login.LoginInteractorListener
import com.example.santantest.domain.model.UserAccount
import com.example.santantest.domain.utils.AppUtils
import com.example.santantest.ui.HomeActivity
import com.example.santantest.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginPresenter(val activity: LoginActivity, val context: Context) :
    LoginInteractorListener {

    override fun onLoginSuccess(user: UserAccount) {
        var home = Intent(context, HomeActivity::class.java)
        var b = Bundle().apply {
            this.putSerializable("user", user)
        }
        home.putExtras(b)

        activity.startActivity(home)
        activity.finish()
    }

    override fun onLoginError() {
        Toast.makeText(context, R.string.login_error_message, Toast.LENGTH_SHORT)
    }

    fun validateForm() : Boolean {
        var hasError = false
        activity.apply {
            var user = this.etUser.text.toString()
            var pwd = this.etPassword.text.toString()

            if (AppUtils.validateEmail(user) || AppUtils.validateCPF(user)) {
                this.etUser.error = null
            } else {
                this.etUser.error = getString(R.string.invalid_login)
                hasError = true
            }

            if (AppUtils.validatePassword(pwd)) {
                this.etPassword.error = null
            } else {
                this.etPassword.error = getString(R.string.invalid_password)
                hasError = true
            }

        }
        return !hasError
    }
}



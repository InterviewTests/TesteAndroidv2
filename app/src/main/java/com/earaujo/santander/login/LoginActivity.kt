package com.earaujo.santander.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.earaujo.santander.R
import com.earaujo.santander.repository.models.LoginRequest
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.ref.WeakReference

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var loginInteractorInput: LoginInteractorInput
    lateinit var loginRouter: LoginRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupHomeActivity()
        btn_login.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            loginInteractorInput.performLogin(LoginRequest(username, password))
        }
    }

    fun setupHomeActivity() {
        loginRouter = LoginRouter()

        loginInteractorInput = LoginInteractor(this).also {
            it.loginPresenterInput = LoginPresenter().also {
                it.loginActivityInput = WeakReference(this)
            }
        }
    }

    override fun displayData(loginModel: LoginActivityModel) {
        tv_error_message.visibility = View.INVISIBLE
    }

    override fun displayErrorMessage(errorMessage: String) {
        tv_error_message.visibility = View.VISIBLE
        tv_error_message.text = errorMessage
    }
}

interface LoginActivityInput {
    fun displayData(loginModel: LoginActivityModel)
    fun displayErrorMessage(errorMessage: String)
}

package com.earaujo.santander.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        btn_hello.setOnClickListener {
            loginInteractorInput.performLogin(LoginRequest("username", "password"))
        }
    }

    fun setupHomeActivity() {
        loginRouter = LoginRouter()

        loginInteractorInput = LoginInteractor().also {
            it.loginPresenterInput = LoginPresenter().also {
                it.loginActivityInput = WeakReference<LoginActivityInput>(this)
            }
        }
    }

    override fun displayData(loginModel: LoginActivityModel) {
        btn_hello.text = loginModel.result
    }
}

interface LoginActivityInput {
    fun displayData(loginModel: LoginActivityModel)
}

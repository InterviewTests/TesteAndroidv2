package com.qintess.santanderapp.ui.view.loginScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qintess.santanderapp.R
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun checkLastUser()
    fun displayLastUser(username: String)
    fun login()
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {
    var output: LoginInteractorInput? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun checkLastUser() {
        output?.checkLastUser()
    }

    override fun displayLastUser(username: String) {
        TODO("Not yet implemented")
    }

    override fun login() {
        val loginRequest = LoginRequest()
        loginRequest.username = usernameField?.text.toString()
        loginRequest.password = passwordField?.text.toString()
        output?.login(loginRequest)
    }
}
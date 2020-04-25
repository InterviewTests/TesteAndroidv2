package com.tata.bank.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tata.bank.R
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun displayError(message: String)
    fun fillLoginFields(user: String, password: String)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {
    lateinit var output: LoginInteractorInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginConfigurator.INSTANCE.configure(this)

        output.verifyLogin()
        btn_login.setOnClickListener { performLogin() }
    }

    private fun performLogin() {
        tv_error.visibility = View.GONE

        val user = et_name.text.toString()
        val password = et_password.text.toString()
        output.doLogin(user, password)
    }

    override fun displayError(message: String) {
        tv_error.visibility = View.VISIBLE
        tv_error.text = message
    }

    override fun fillLoginFields(user: String, password: String) {
        et_name.setText(user)
        et_password.setText(password)
    }
}

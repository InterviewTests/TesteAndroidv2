package com.tata.bank.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tata.bank.R
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun displayError(message: String)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {
    lateinit var output: LoginInteractorInput
    lateinit var router: LoginRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginConfigurator.INSTANCE.configure(this)

        btn_login.setOnClickListener { performLogin() }
    }

    private fun performLogin() {
        tv_error.visibility = View.GONE

        val user = et_name.text.toString()
        val password = et_password.text.toString()
        output.fetchLogin(user, password)
    }

    override fun displayError(message: String) {
        tv_error.visibility = View.VISIBLE
        tv_error.text = message
    }
}

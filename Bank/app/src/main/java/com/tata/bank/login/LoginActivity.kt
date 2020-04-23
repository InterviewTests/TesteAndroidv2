package com.tata.bank.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tata.bank.R

interface LoginActivityInput {
//    fun displayHomeMetaData(loginViewModel: LoginViewModel)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var output: LoginInteractorInput
    lateinit var router: LoginRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginConfigurator.INSTANCE.configure(this)
    }
}

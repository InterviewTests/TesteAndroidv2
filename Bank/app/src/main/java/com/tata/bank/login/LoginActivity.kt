package com.tata.bank.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tata.bank.R
import kotlinx.android.synthetic.main.activity_login.*

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

        btn_login.setOnClickListener { performLogin() }
    }

    private fun performLogin() {
        // create Request and set the needed input
//        val loginRequest = LoginRequest()
        // Call the output to fetch the data
//        output.fetchHomeMetaData(homeRequest);
    }
}

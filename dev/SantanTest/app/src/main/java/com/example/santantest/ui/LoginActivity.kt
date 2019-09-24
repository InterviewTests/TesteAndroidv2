package com.example.santantest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.santantest.R
import com.example.santantest.domain.interactor.login.LoginInteractor
import com.example.santantest.domain.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val interactor = LoginInteractor()
    val presenter = LoginPresenter(this@LoginActivity,this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        setEvents()
    }

    fun setEvents(){
        btLogin.setOnClickListener {
            if (presenter.validateForm()){
                interactor.doLogin(
                    etUser.text.toString(),
                    etPassword.text.toString(),
                    presenter
                )
            }

        }

    }





}

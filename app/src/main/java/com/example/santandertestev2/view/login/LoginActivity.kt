package com.example.santandertestev2.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.santandertestev2.R
import com.example.santandertestev2.domain.Util.AppUtil
import com.example.santandertestev2.domain.controller.login.LoginController
import com.example.santandertestev2.domain.presenter.ILoginPresenter
import com.example.santandertestev2.domain.presenter.LoginPresenter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(), ILoginPresenter {

    private lateinit var fieldLogin: TextInputLayout
    private lateinit var fieldPassword: TextInputLayout
    private lateinit var btnLogin: MaterialButton
    private lateinit var load : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val presenter = LoginPresenter(this)
        val loginController = LoginController(this, presenter)
        loginController.autoLogin()

        this.fieldLogin = findViewById(R.id.fieldlogin)
        this.fieldPassword = findViewById(R.id.fieldpassword)
        this.load = findViewById(R.id.load)

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            val login = fieldLogin.editText?.text.toString()
            val pass = fieldPassword.editText?.text.toString()
            if ( AppUtil.validateEmail(login) || AppUtil.validateCPF(login)){
                this.fieldLogin.error = null
            }else{
                this.fieldLogin.error = getString(R.string.errorLoginInvalid)
            }

            if(!AppUtil.validatePassword(pass)){
                this.fieldPassword.error = getString(R.string.errorPasswordInvalid)
            }else{
                this.fieldPassword.error =null
                this.load.visibility = View.VISIBLE
                loginController.getLogin(login, pass)
            }
        }

    }

    override fun onLoginSuccessfull(intent: Intent) {
        startActivity(intent)
        this.finish()
    }

    override fun onLoginError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        this.load.visibility = View.GONE
    }

}

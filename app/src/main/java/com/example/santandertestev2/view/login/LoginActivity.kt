package com.example.santandertestev2.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.example.santandertestev2.R
import com.example.santandertestev2.domain.Util.AppUtil
import com.example.santandertestev2.domain.model.controller.UserAccount
import com.example.santandertestev2.domain.controller.login.ILogin
import com.example.santandertestev2.domain.controller.login.LoginController
import com.example.santandertestev2.view.detail.DetailActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(),
    ILogin {


    private lateinit var fieldLogin: TextInputLayout
    private lateinit var fieldPassword: TextInputLayout
    private lateinit var btnLogin: MaterialButton
    private lateinit var load : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginController = LoginController(this)
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

    override fun onLoginSuccessfull(user : UserAccount) {
        var detailscreen = Intent(this, DetailActivity::class.java)
        var b = Bundle().apply {
            this.putSerializable("user", user)
        }
        detailscreen.putExtras(b)
        startActivity(detailscreen)
        this.finish()
    }

    override fun onLoginError() {
        load.visibility = View.GONE
    }

}

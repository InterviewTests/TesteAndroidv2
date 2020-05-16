package com.lucianogiardino.bankapp.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lucianogiardino.bankapp.R
import com.lucianogiardino.bankapp.presentation.statement.StatementActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(),
    LoginContract.View {

    private val loginPresenter: LoginContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter.hasLoggedUser()

        btnLogin.setOnClickListener {
            loginPresenter.login(etUsername.editText?.text.toString(),etPassword.editText?.text.toString())
        }

        etUsername.editText?.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                loginPresenter.validateUsername(etUsername.editText?.text.toString())
            }
        }

        etPassword.editText?.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                loginPresenter.validatePassword(etPassword.editText?.text.toString())
            }
        }

    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun showUsernameError(hasError:Boolean, msg: String) {
        if(hasError){
            etUsername.error = msg
        }else{
            etUsername.error = ""
        }
    }

    override fun showPasswordError(hasError:Boolean, msg: String) {
        if(hasError){
            etPassword.error = msg
        }else{
            etPassword.error = ""
        }

    }

    override fun enableLogin() {
        btnLogin.isEnabled = true
    }

    override fun goToStatement() {
        val intent = Intent(this, StatementActivity::class.java)
        startActivity(intent)
        finish()
    }
}


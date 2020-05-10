package com.lucianogiardino.bankapp.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lucianogiardino.bankapp.R
import com.lucianogiardino.bankapp.presentation.statement.StatementActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(),
    LoginContract.View {

    private val loginPresenter: LoginContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginPresenter.hasLoggedUser()

        btnLogin.setOnClickListener {
            loginPresenter.validateUser(etUsername.text.toString(),etPassword.text.toString())
        }
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun goToStatement() {
        val intent = Intent(this, StatementActivity::class.java)
        startActivity(intent)
        finish()
    }
}


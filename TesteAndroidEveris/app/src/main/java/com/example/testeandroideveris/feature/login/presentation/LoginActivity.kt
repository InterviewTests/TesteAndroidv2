package com.example.testeandroideveris.feature.login.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testeandroideveris.R
import com.example.testeandroideveris.data.Status
import com.example.testeandroideveris.feature.login.data.LoginDataState
import com.example.testeandroideveris.feature.statements.presentation.StatementsActivity
import com.example.testeandroideveris.feature.statements.presentation.USER_DATA
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener { viewModel.login(edtUser.text.toString(), edtPassword.text.toString()) }

        lastUserObserve()
        dataStateObserve()
        loginObserve()
    }

    private fun lastUserObserve() {
        viewModel.getLastUser().observe(this, Observer {
            edtUser.setText(it ?: "")
        })
    }

    private fun dataStateObserve() {
        viewModel.dataState.observe(this, Observer { state ->
            when(state) {
                LoginDataState.INVALID_USER -> edtUser.error = "User invalido"
                LoginDataState.INVALID_PASSWORD -> edtPassword.error = "Password invalido"
                else -> {}
            }
        })
    }

    private fun loginObserve() {
        viewModel.login.observe(this, Observer { login ->
            when(login.status) {
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    val intent = Intent(this, StatementsActivity::class.java)
                    intent.putExtra(USER_DATA, login.data)
                    startActivity(intent)
                }
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    Toast.makeText(this, "Erro ao realizar o login" , Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> progress.visibility = View.VISIBLE
            }
        })
    }
}
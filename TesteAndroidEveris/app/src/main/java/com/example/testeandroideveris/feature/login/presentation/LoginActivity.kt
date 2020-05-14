package com.example.testeandroideveris.feature.login.presentation

import android.content.Intent
import android.os.Bundle
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
        actionBar?.hide()

        loginButton.setOnClickListener { viewModel.login(edtUser.text.toString(), edtPassword.text.toString()) }

        viewModel.dataState.observe(this, Observer { state ->
            when(state) {
                LoginDataState.INVALID_USER -> edtUser.error = "User invalido"
                LoginDataState.INVALID_PASSWORD -> edtPassword.error = "Password invalido"
                LoginDataState.VALID_SUCCESS -> {Toast.makeText(this, "Sucesso" , Toast.LENGTH_LONG).show()}
            }
        })

        viewModel.login.observe(this, Observer { login ->
            when(login.status) {
                Status.SUCCESS -> {
                    val intent = Intent(this, StatementsActivity::class.java)
                    intent.putExtra(USER_DATA, login.data)
                    startActivity(intent)
                }
                Status.ERROR -> Toast.makeText(this, "Erro ao realizar o login" , Toast.LENGTH_LONG).show()
                Status.LOADING -> {}
            }
        })
    }
}
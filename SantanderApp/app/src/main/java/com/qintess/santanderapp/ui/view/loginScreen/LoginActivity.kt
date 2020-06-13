package com.qintess.santanderapp.ui.view.loginScreen

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.qintess.santanderapp.R
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun createListeners()
    fun checkLastUser()
    fun displayLastUser(username: String)
    fun login()
    fun showAlert(msg: String): Boolean
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {
    var output: LoginInteractorInput? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginConfigurator.INSTANCE.configure(this)
        createListeners()
    }

    override fun createListeners() {
        loginButton.setOnClickListener {
            login()
        }
    }

    override fun checkLastUser() {
        output?.checkLastUser()
    }

    override fun displayLastUser(username: String) {
        usernameField.setText(username)
    }

    override fun login() {
        val loginRequest = LoginRequest()
        loginRequest.username = usernameField
        loginRequest.password = passwordField
        output?.login(loginRequest)
    }

    override fun showAlert(msg: String): Boolean {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Credenciais inválidas")
        alertDialog.setMessage(msg)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
        return true // somente para saber que toda execução acima aconteceu sem erros
    }
}
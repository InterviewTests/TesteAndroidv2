package com.qintess.santanderapp.ui.view.loginScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.qintess.santanderapp.R
import com.qintess.santanderapp.helper.Prefs
import com.qintess.santanderapp.model.UserModel
import com.qintess.santanderapp.ui.view.statementsScreen.StatementsActivity
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun createListeners()
    fun checkLastUser()
    fun displayLastUser(username: String)
    fun login()
    fun showAlert(title: String, msg: String): Boolean
    fun goToStatements(user: UserModel)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {
    var output: LoginInteractorInput? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginConfigurator.INSTANCE.configure(this)
        checkLastUser()
        createListeners()
    }

    override fun createListeners() {
        loginButton.setOnClickListener {
            login()
        }
    }

    override fun checkLastUser() {
        val username = Prefs(this).getString(Prefs.Key.LAST_USER)
        output?.checkLastUser(username)
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

    override fun showAlert(title: String, msg: String): Boolean {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(msg)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
        return true // somente para saber que toda execução acima aconteceu sem erros
    }

    override fun goToStatements(user: UserModel) {
        Prefs(this).putString(Prefs.Key.LAST_USER, usernameField.text.toString())
        val intent = Intent(this, StatementsActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }
}
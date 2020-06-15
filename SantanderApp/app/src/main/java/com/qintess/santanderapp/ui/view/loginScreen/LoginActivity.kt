package com.qintess.santanderapp.ui.view.loginScreen

import android.content.Intent
import android.os.Bundle
import com.qintess.santanderapp.R
import com.qintess.santanderapp.helper.Prefs
import com.qintess.santanderapp.model.UserModel
import com.qintess.santanderapp.ui.view.AppActivity
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

class LoginActivity : AppActivity(), LoginActivityInput {
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
        val username = Prefs(this).getString(Prefs.Key.LastUser)
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

    override fun goToStatements(user: UserModel) {
        Prefs(this).putString(Prefs.Key.LastUser, usernameField.text.toString())
        val intent = Intent(this, StatementsActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }
}
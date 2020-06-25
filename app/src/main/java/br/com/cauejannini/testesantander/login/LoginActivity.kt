package br.com.cauejannini.testesantander.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import br.com.cauejannini.testesantander.R
import br.com.cauejannini.testesantander.commons.SharedPreferencesHelper
import br.com.cauejannini.testesantander.commons.Utils
import br.com.cauejannini.testesantander.commons.form.Form
import br.com.cauejannini.testesantander.commons.form.validators.PasswordValidator
import br.com.cauejannini.testesantander.commons.form.validators.UsernameValidator
import br.com.cauejannini.testesantander.statements.StatementsActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun onLoggedIn(userAccount: UserAccount)
    fun onLoginError(message: String)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    lateinit var output: LoginInteractorInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginConfigurator.configure(this)

        inputUser.setValidator(UsernameValidator())
        inputPassword.setValidator(PasswordValidator())

        val form = Form(btLogin)
        form.addValidatable(inputUser)
        form.addValidatable(inputPassword)

        btLogin.setOnClickListener {
            login()
        }

        inputPassword.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            hideKeyboard()
            login()
            true
        })

        SharedPreferencesHelper.getLastLoggedUser(this)?.let {
            inputUser.text = it
        }
    }

    fun login() {
        val user = inputUser.text
        val password = inputPassword.text
        output.login(LoginRequest(user, password))
        btLogin.setLoading(true)
    }

    override fun onLoggedIn(userAccount: UserAccount) {
        Log.i("userAccount", Gson().toJson(userAccount))
        btLogin.setLoading(false)

        SharedPreferencesHelper.saveLoggedUser(this, inputUser.text)

        val intent = Intent(this, StatementsActivity::class.java)
        intent.putExtra(StatementsActivity.EXTRA_KEY_USER_ACCOUNT, userAccount)
        startActivity(intent)
    }

    override fun onLoginError(message: String) {
        Utils.showToast(this, message)
        btLogin.setLoading(false)
    }

    fun AppCompatActivity.hideKeyboard() {
        val view = currentFocus ?: View(this)
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
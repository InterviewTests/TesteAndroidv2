package dev.ornelas.bankapp.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import dev.ornelas.bankapp.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginContract.Presenter

    lateinit var router: LoginContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LoginConfigurator.configure(this)

        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        presenter.onViewCreated()

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            login.isEnabled = false
            presenter.onLogin(usernameInput.text.toString(), passwordInput.text.toString())
        }

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun displayLoginResult(result: LoginViewModel) {
        loading.visibility = View.INVISIBLE
        login.isEnabled = true
        if (result.usernameError != null) {
            usernameInput.error = getString(result.usernameError)
        }
        if (result.passwordError != null) {
            passwordInput.error = getString(result.passwordError)
        }

        if (result.passwordError != null || result.usernameError != null) {
            return
        }

        if (result.error != null) {
            showLoginFailed(result.error)
            return
        }
        
        result.success?.let {
            router.navigateToStatements(it)
        }

    }
}

package br.com.alex.bankappchallenge.feature.login

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import br.com.alex.bankappchallenge.R
import br.com.alex.bankappchallenge.extensions.observeNonNull
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginRouter = LoginRouter(this)
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lifecycle.addObserver(loginViewModel)
        initViewModel()
        actionLoginButton()
    }

    private fun actionLoginButton() {
        buttonLogin.setOnClickListener {
            loginViewModel.execute(
                LoginIntentions.Login(
                    editTextUser.text.toString(),
                    editTextPassword.text.toString()
                )
            )
        }
    }

    private fun initViewModel() {
        loginViewModel.states.observeNonNull(this) {
            with(it) {
                when {
                    isLoading -> showLoading()
                    isLoadError -> showError(errorMessage)
                    isUserEmpty -> showError(R.string.emptyUser, editTextUser)
                    isPasswordEmpty -> showError(R.string.emptyPassowrd, editTextPassword)
                    isPasswordInvalid -> showError(R.string.passwordInvalid, editTextPassword)
                    userLogin.isNotEmpty() -> showUser(userLogin)
                }
            }
        }

        loginViewModel.navigations.observeNonNull(this) {
            it.getContentIfNotHandled()?.let {
                loginRouter.navigateToStatement()
            }
        }
    }

    private fun showUser(userLogin: String) {
        editTextUser.setText(userLogin)
        editTextPassword.requestFocus()
    }

    private fun showError(errorMessage: String) {
        editTextPassword.error = errorMessage
        editTextPassword.requestFocus()
    }

    private fun showError(@StringRes messageResource: Int, editText: EditText) {
        editText.error = getString(messageResource)
        editText.requestFocus()
    }

    private fun showLoading() {
        Log.i("LoginActivity", "showLoading()")
    }
}

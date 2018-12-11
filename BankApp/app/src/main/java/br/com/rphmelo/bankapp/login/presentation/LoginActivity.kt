package br.com.rphmelo.bankapp.login.presentation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.rphmelo.bankapp.R
import br.com.rphmelo.bankapp.login.api.LoginService
import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import br.com.rphmelo.bankapp.currency.presentation.CurrencyActivity
import br.com.rphmelo.bankapp.login.domain.models.LoginView
import br.com.rphmelo.bankapp.login.repository.LoginRepository
import br.com.rphmelo.bankapp.login.domain.interactor.LoginInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val loginRepository = LoginRepository(LoginService())
    private val presenter = LoginPresenter(
            this, LoginInteractor(loginRepository)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            login()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun setUserEmptyError() {
        showErrorMessage(getString(R.string.empty_input_message))
    }

    override fun setPasswordEmptyError() {
        showErrorMessage(getString(R.string.empty_input_message))
    }

    override fun setUserInvalidError() {
        showErrorMessage(getString(R.string.invalid_user_message))
    }

    override fun setPasswordInvalidError() {
        showErrorMessage(getString(R.string.invalid_password_message))
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        val currencyIntent = Intent(this, CurrencyActivity::class.java)
        currencyIntent.putExtra("LOGIN_RESPONSE", loginResponse.toString())
        startActivity(currencyIntent)
        finish()
    }

    override fun onLoginError() {
        showErrorMessage(getString(R.string.login_error_message))
    }

    private fun login(){
        presenter.login(LoginRequest(tietUser.text.toString(), tietPassword.text.toString()))
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this,
                message, Toast.LENGTH_LONG).show();
    }
}

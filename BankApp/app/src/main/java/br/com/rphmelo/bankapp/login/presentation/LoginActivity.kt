package br.com.rphmelo.bankapp.login.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.rphmelo.bankapp.R
import br.com.rphmelo.bankapp.common.utils.ErrorMessage
import br.com.rphmelo.bankapp.common.utils.GsonHelper
import br.com.rphmelo.bankapp.common.utils.Variables
import br.com.rphmelo.bankapp.login.api.LoginService
import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import br.com.rphmelo.bankapp.currency.presentation.CurrencyActivity
import br.com.rphmelo.bankapp.login.domain.models.LoginView
import br.com.rphmelo.bankapp.login.repository.LoginRepository
import br.com.rphmelo.bankapp.login.domain.interactor.LoginInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var preferences: SharedPreferences
    private lateinit var loginRepository: LoginRepository
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferences = getSharedPreferences(Variables.PREFERENCES_NAME, Context.MODE_PRIVATE)

        loginRepository = LoginRepository(LoginService(), preferences)
        presenter = LoginPresenter(
                this, LoginInteractor(loginRepository)
        )

        presenter.loadLoginSession()

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
        currencyIntent.putExtra(Variables.LOGIN_RESPONSE_KEY, GsonHelper().toJson(loginResponse))

        startActivity(currencyIntent)
        finish()
    }

    override fun onLoginError() {
        showErrorMessage(getString(R.string.login_error_message))
    }

    override fun onLoadLoginSession(login: LoginRequest?) {
        tietUser.setText(login?.user)
        tietPassword.setText(login?.password)
    }
    private fun login(){
        val loginRequest = LoginRequest(tietUser.text.toString(), tietPassword.text.toString())
        presenter.setLoginSession(loginRequest)
        presenter.login(loginRequest)
    }

    private fun showErrorMessage(message: String) {
        ErrorMessage().showErrorMessage(this, message)
    }
}

package br.com.rphmelo.bankapp.presentation.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.rphmelo.bankapp.R
import br.com.rphmelo.bankapp.presentation.currency.CurrencyActivity
import br.com.rphmelo.bankapp.presentation.login.models.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter = LoginPresenter(this, LoginInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            validateCredentials()
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

    override fun navigateToCurrencyPage() {
        startActivity(Intent(this, CurrencyActivity::class.java))
    }

    private fun validateCredentials(){
        presenter.validateCredentials(tietUser.text.toString(), tietPassword.text.toString())
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this,
                message, Toast.LENGTH_LONG).show();
    }
}

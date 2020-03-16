package dev.vitorpacheco.solutis.bankapp.loginScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.idling.CountingIdlingResource
import com.google.common.base.Strings
import dev.vitorpacheco.solutis.bankapp.BankApp.Companion.LAST_LOGGED_USER_KEY
import dev.vitorpacheco.solutis.bankapp.R
import dev.vitorpacheco.solutis.bankapp.workers.SharedPreferencesRequest
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun displayLoginData(viewModel: LoginViewModel)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    var output: LoginInteractorInput? = null
    var router: LoginRouter? = null

    lateinit var idlingResource: CountingIdlingResource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        idlingResource = CountingIdlingResource(LoginActivity::class.java.simpleName)

        LoginConfigurator.INSTANCE.configure(this)

        idlingResource.increment()

        loginButton.setOnClickListener {
            toggleForm(true)

            val loginRequest = LoginRequest(
                userField.text.toString(),
                passwordField.text.toString(),
                this
            )

            output?.doLogin(loginRequest)
        }

        output?.getLastLoggedUser(SharedPreferencesRequest(this, LAST_LOGGED_USER_KEY))
    }

    override fun displayLoginData(viewModel: LoginViewModel) {
        userFieldLayout.error = null
        passwordFieldLayout.error = null

        if (viewModel.errorMessage != null) {
            toggleForm(false)

            userFieldLayout.error = viewModel.errorMessage
            userFieldLayout.isErrorEnabled = !Strings.isNullOrEmpty(viewModel.errorMessage)

            return
        }

        userFieldLayout.error = null

        viewModel.account?.let {
            val intent = router?.navigateToStatements(it)

            startActivity(intent)
            finish()

            idlingResource.decrement()
        }

        viewModel.user?.let {
            userField.setText(it)
            userFieldLayout.error = null
        }


        if (viewModel.invalidUser == true) {
            userFieldLayout.error = getString(R.string.error_invalid_user)
        }

        if (viewModel.invalidPassword == true) {
            passwordFieldLayout.error = getString(R.string.error_invalid_password)
        }
    }

    private fun toggleForm(loading: Boolean) {
        userFieldLayout.isEnabled = !loading
        passwordFieldLayout.isEnabled = !loading
        loginButton.isEnabled = !loading
        loginButton.setText(if (!loading) R.string.login else R.string.loading)
    }

    companion object {
        var TAG = LoginActivity::class.java.simpleName
    }

}

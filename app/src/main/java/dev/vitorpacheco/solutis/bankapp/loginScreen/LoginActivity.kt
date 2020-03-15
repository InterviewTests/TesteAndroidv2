package dev.vitorpacheco.solutis.bankapp.loginScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.common.base.Strings
import dev.vitorpacheco.solutis.bankapp.R
import kotlinx.android.synthetic.main.activity_login.*


interface LoginActivityInput {
    fun displayLoginData(viewModel: LoginViewModel)
}

class LoginActivity : AppCompatActivity(), LoginActivityInput {

    var output: LoginInteractorInput? = null
    var router: LoginRouter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginConfigurator.INSTANCE.configure(this)

        loginButton.setOnClickListener {
            val loginRequest = LoginRequest(
                userField.text.toString(),
                passwordField.text.toString()
            )

            output?.doLogin(loginRequest)
        }
    }

    override fun displayLoginData(viewModel: LoginViewModel) {
        if (viewModel.errorMessage != null) {
            userFieldLayout.error = viewModel.errorMessage
            userFieldLayout.isErrorEnabled = !Strings.isNullOrEmpty(viewModel.errorMessage)

            return
        }

        userFieldLayout.error = null
        userField.text = null
        passwordField.text = null

        viewModel.account?.let {
            val intent = router?.navigateToStatements(it)

            startActivity(intent)
            finish()
        }
    }

    companion object {
        var TAG = LoginActivity::class.java.simpleName
    }

}

package dev.vitorpacheco.solutis.bankapp.loginScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.idling.CountingIdlingResource
import com.google.common.base.Strings
import dev.vitorpacheco.solutis.bankapp.R
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

        viewModel.account?.let {
            val intent = router?.navigateToStatements(it)

            startActivity(intent)
            finish()

            idlingResource.decrement()
        }
    }

    companion object {
        var TAG = LoginActivity::class.java.simpleName
    }

}

package io.github.maikotrindade.bankapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.login.model.Field
import io.github.maikotrindade.bankapp.login.model.FieldError
import io.github.maikotrindade.bankapp.login.model.User
import io.github.maikotrindade.bankapp.login.model.UserData
import kotlinx.android.synthetic.main.fragment_login.*

interface LoginFragmentInput {
    fun showErrorMessage(vararg error: FieldError)
    fun navigateToStatementsScreen(userAccount: UserData)
    fun startLogin(user: User)
    fun showLoginError(errorMessage: String? = null)
}

class LoginFragment : Fragment(), LoginFragmentInput {

    var interactor: LoginInteractor? = null
    var router: LoginRouter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoginConfigurator.INSTANCE.configure(this)
        bindUserEvents()
    }

    private fun bindUserEvents() {
        btnLogin.setOnClickListener {
            val email = txtInputEmail.text.toString()
            val password = txtInputPassword.text.toString()
            val user = User(email, password)
            interactor?.logIn(user)
        }
    }

    override fun showErrorMessage(vararg error: FieldError) {
        for (err in error) {
            when (err.fieldError) {
                Field.LOGIN -> txtInputEmail.error = getString(err.errorMessage)
                else -> txtInputPassword.error = getString(err.errorMessage)
            }
        }
    }

    override fun navigateToStatementsScreen(userAccount: UserData) {
        router?.navigateToStatementScreen(userAccount)
    }

    override fun startLogin(user: User) {
        interactor?.startLogin(user)
    }

    override fun showLoginError(errorMessage: String?) {
        val errorMessageString = errorMessage ?: getString(R.string.error_signin)
        val errorSnackbar = Snackbar.make(containerView, errorMessageString, LENGTH_LONG)
        errorSnackbar.show()
    }

}

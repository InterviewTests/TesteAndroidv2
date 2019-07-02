package io.github.maikotrindade.bankapp.login.domain

import android.util.Patterns
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.login.ui.LoginFragmentInput
import io.github.maikotrindade.bankapp.login.model.Field
import io.github.maikotrindade.bankapp.login.model.FieldError
import io.github.maikotrindade.bankapp.login.model.User
import java.lang.ref.WeakReference

interface HomePresenterInput {
    fun logIn(user: User)
}

class LoginPresenter : HomePresenterInput {

    lateinit var fragmentInput: WeakReference<LoginFragmentInput>

    override fun logIn(user: User) {
        validate(user)
    }

    private fun validate(user: User) {
        if (isEmailValid(user.user) && isPasswordValid(user.password)) {
            fragmentInput.get()!!.startLogin(user)
        } else if (!isEmailValid(user.user) && isPasswordValid(user.password)) {
            fragmentInput.get()!!.showErrorMessage(FieldError(R.string.error_invalid_email, Field.LOGIN))
        } else if (isEmailValid(user.user) && !isPasswordValid(user.password)) {
            fragmentInput.get()!!.showErrorMessage(FieldError(R.string.error_invalid_password, Field.PASSWORD))
        } else {
            fragmentInput.get()!!.showErrorMessage(FieldError(R.string.error_invalid_password, Field.PASSWORD),
                FieldError(R.string.error_invalid_email, Field.LOGIN))
        }
    }

    private fun isEmailValid(login: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(login).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[^\\w])).+\$".toRegex())
    }


}
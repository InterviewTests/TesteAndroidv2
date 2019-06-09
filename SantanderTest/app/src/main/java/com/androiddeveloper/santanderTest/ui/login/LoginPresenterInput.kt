package com.androiddeveloper.santanderTest.ui.login

import android.util.Patterns
import com.androiddeveloper.santanderTest.R
import java.lang.ref.WeakReference

class LoginPresenterInput : ILoginContract.LoginPresenterInput {

    var output: WeakReference<ILoginContract.LoginActInput>? = null

    override fun isUserValid(username: String, password: String) {
        when {
            !isUsernameValid(username) && !isPasswordValid(password) ->
                output?.get()?.onInvalidLogin(R.string.username_password_message_error)
            !isUsernameValid(username) ->
                output?.get()?.onInvalidLogin(R.string.username_message_error)
            !isPasswordValid(password) ->
                output?.get()?.onInvalidLogin(R.string.password_message_error)
            else -> {
                output?.get()?.onValidLogin(username, password)
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[#\$@!%&*?])[A-Za-z\\d#\$@!%&*?]*\$".toRegex())
    }

    private fun isUsernameValid(username: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches() ||
                username.matches("(?=^.{11}\$)^[0-9]*\$".toRegex())
    }
}
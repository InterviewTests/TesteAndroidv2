package dev.vitorpacheco.solutis.bankapp.loginScreen

import dev.vitorpacheco.solutis.bankapp.BankApp.Companion.LAST_LOGGED_USER_KEY
import dev.vitorpacheco.solutis.bankapp.extensions.isValidCpf
import dev.vitorpacheco.solutis.bankapp.extensions.isValidEmail
import dev.vitorpacheco.solutis.bankapp.extensions.isValidPassword
import dev.vitorpacheco.solutis.bankapp.workers.SharedPreferencesRequest
import dev.vitorpacheco.solutis.bankapp.workers.SharedPreferencesWorker

interface LoginInteractorInput {
    fun doLogin(request: LoginRequest)
    fun getLastLoggedUser(request: SharedPreferencesRequest)
}

class LoginInteractor : LoginInteractorInput {

    var output: LoginPresenterInput? = null
    var loginWorker = LoginWorker()
    var sharedPreferencesWorker = SharedPreferencesWorker()

    override fun doLogin(request: LoginRequest) {
        if (!request.user.isValidEmail() && !request.user.isValidCpf()) {
            output?.presentLoginData(LoginResponse(invalidUser = true))
            return
        }

        if (!request.password.isValidPassword()) {
            output?.presentLoginData(LoginResponse(invalidPassword = true))
            return
        }

        loginWorker.login(request) { response ->
            request.context?.let { context ->
                sharedPreferencesWorker.save(
                    SharedPreferencesRequest(
                        context,
                        LAST_LOGGED_USER_KEY,
                        request.user
                    )
                )
            }

            output?.presentLoginData(response)
        }
    }

    override fun getLastLoggedUser(request: SharedPreferencesRequest) {
        output?.presentLoginData(LoginResponse(user = sharedPreferencesWorker.get(request)))
    }

    companion object {
        var TAG = LoginInteractor::class.java.simpleName
    }

}

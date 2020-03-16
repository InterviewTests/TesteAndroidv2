package dev.vitorpacheco.solutis.bankapp.loginScreen

import com.google.common.base.Strings
import dev.vitorpacheco.solutis.bankapp.BankApp.Companion.LAST_LOGGED_USER_KEY
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
        if (Strings.isNullOrEmpty(request.user)) {
            output?.presentLoginData(
                LoginResponse(
                    error = UserError(
                        "Campo obrigatório",
                        UserFormFields.USER
                    )
                )
            )
            return
        }

        if (Strings.isNullOrEmpty(request.password)) {
            output?.presentLoginData(
                LoginResponse(
                    error = UserError(
                        "Campo obrigatório",
                        UserFormFields.PASSWORD
                    )
                )
            )
            return
        }

        loginWorker.login(request) { response ->
            request.context?.let { context ->
                sharedPreferencesWorker.save(SharedPreferencesRequest(context, LAST_LOGGED_USER_KEY, request.user))
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

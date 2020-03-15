package dev.vitorpacheco.solutis.bankapp.loginScreen

import com.google.common.base.Strings

interface LoginInteractorInput {
    fun doLogin(request: LoginRequest)
}

class LoginInteractor : LoginInteractorInput {

    var output: LoginPresenterInput? = null
    var worker = LoginWorker()

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

        worker.login(request) { output?.presentLoginData(it) }
    }

    companion object {
        var TAG = LoginInteractor::class.java.simpleName
    }

}

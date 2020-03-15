package dev.vitorpacheco.solutis.bankapp.loginScreen

import dev.vitorpacheco.solutis.bankapp.api.BankService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface LoginInteractorInput {
    fun doLogin(request: LoginRequest)
}

class LoginInteractor : LoginInteractorInput {

    var output: LoginPresenterInput? = null
    private var loginWorkerInput: LoginWorkerInput? = null

    override fun doLogin(request: LoginRequest) {
        loginWorkerInput = LoginWorker()

        BankService.create().login(request.user, request.password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    response.body()?.let {
                        output?.presentLoginData(it)
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    output?.presentLoginData(LoginResponse(error = UserError(t.message)))
                }
            })
    }

    companion object {
        var TAG = LoginInteractor::class.java.simpleName
    }

}

package br.com.cauejannini.testesantander.login

import android.content.Context
import br.com.cauejannini.testesantander.commons.integracao.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface LoginInteractorInput {
    fun login(request: LoginRequest)
}

class LoginInteractor: LoginInteractorInput {

    var output: LoginPresenterInput? = null
    val api = ApiRepository().get()

    override fun login(request: LoginRequest) {

        val user = request.user
        val password = request.password

        api.login(user, password).enqueue(object: Callback<LoginResponseModel> {

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                output?.onLoginFailed(if (t.message != null) t.message!! else "Erro")
            }

            override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    output?.onLoginResponse(loginResponse)
                } else {
                    output?.onLoginFailed(response.message())
                }
            }

        })

    }


}
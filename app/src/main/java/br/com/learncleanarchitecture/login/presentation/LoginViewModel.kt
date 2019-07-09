package br.com.learncleanarchitecture.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.learncleanarchitecture.login.data.LoginRepository
import br.com.learncleanarchitecture.login.data.api.Error
import br.com.learncleanarchitecture.login.domain.LoginInteractor
import br.com.learncleanarchitecture.util.DataResponse

class LoginViewModel : ViewModel() {
    fun doLogin(
        request: LoginRequest,
        output: LoginPresenterInput?
    ) {

        Log.d(LoginInteractor.TAG, "In method doLogin")
        val loginResponse = LoginResponse()

        LoginRepository.getInstance().doLogin(request.username, request.password, object : DataResponse<Login>() {
                override fun onThrowable(response: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSuccess(response: Login) {
                    loginResponse.login = response
                    output?.presentLoginMetaData(loginResponse)
                }

                override fun onError(response: Error) {
                    loginResponse.error = response
                    output?.presentLoginMetaData(loginResponse)
                }
            })
    }

    fun verifyHaveUser(output: LoginPresenterInput?) {
        val loginResponse = LoginResponse()

        LoginRepository.getInstance().verifyHaveUser( object : DataResponse<Login>() {
            override fun onThrowable(response: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(response: Login) {
                loginResponse.login = response
                output?.presentLoginInUserFields(loginResponse)
            }

            override fun onError(response: Error) {
//                loginResponse.error = response
//                output?.presentLoginMetaData(loginResponse)
            }
        })
    }
}

data class LoginRequest( var username: String = "", var password: String = "")
data class LoginResponse(var login: Login? = null, var error : Error? = null)